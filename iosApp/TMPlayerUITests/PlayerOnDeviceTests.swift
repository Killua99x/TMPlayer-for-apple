import XCTest

/// Drives the app on a real iPhone or iPad the way a viewer would, for checks that would otherwise
/// need someone at the device (docs/IOS_PORT_PLAN.md): it opens the first video on Home in
/// the player and keeps pictures of what the screen shows in the test's results.
///
/// Run it on a signed in device, with the device unlocked:
/// xcodebuild test -project iosApp/TMPlayer.xcodeproj -scheme TMPlayerDeviceCheck \
///   -destination 'id=<device>' DEVELOPMENT_TEAM=<team> -allowProvisioningUpdates
/// It plays that video in the account's app, as a viewer would.
final class PlayerOnDeviceTests: XCTestCase {
    override func setUp() {
        continueAfterFailure = true
    }

    func testPlayerLoader() throws {
        let app = XCUIApplication()
        app.launch()
        // A debug build takes a while to its first frame.
        sleep(25)
        keep(app, "home")

        // The first card of Home's first row: placed by the window's shape, as the cards carry
        // no names iOS can find.
        let wide = app.windows.firstMatch.frame.width > app.windows.firstMatch.frame.height
        let firstCard = wide ? CGVector(dx: 0.142, dy: 0.312) : CGVector(dx: 0.19, dy: 0.326)
        app.coordinate(withNormalizedOffset: firstCard).tap()
        sleep(4)
        keep(app, "details")

        let play = app.buttons.matching(NSPredicate(format: "label ==[c] 'Play' OR label ==[c] 'Riproduci'")).firstMatch
        guard play.waitForExistence(timeout: 5) else {
            XCTFail("No Play button on the page opened from Home's first card")
            return
        }
        play.tap()
        // The loader, as it comes up and while the stream starts.
        for frame in 0..<12 {
            usleep(700_000)
            keep(app, "loader-\(frame)")
        }
        sleep(4)
        keep(app, "playing")
    }

    /// A picture of the screen now, kept in the test's results under [name].
    private func keep(_ app: XCUIApplication, _ name: String) {
        let shot = XCTAttachment(screenshot: XCUIScreen.main.screenshot())
        shot.name = name
        shot.lifetime = .keepAlways
        add(shot)
    }
}
