# CarmudiTakeHomeExam

An Unit Android Test is a test that needs an Android device or emulator but it's different from a UI test because it doesn't start any activities.

In this sample the test can't run without the Android Framework because the Parcel class is used in one of the methods of the Parcelable interface and the way data is written into a Parcel and read from it is not trivial enough to be stubbed.

Note that the unit test is placed in /androidTest/ instead of /test/.

This project uses the Gradle build system. You can either benefit from IDEs integration such as Android studio or run the tests on the command line.
