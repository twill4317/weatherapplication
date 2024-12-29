## Add project specific ProGuard rules here.
## You can control the set of applied configuration files using the
## proguardFiles setting in build.gradle.
##
## For more details, see
##   http://developer.android.com/guide/developing/tools/proguard.html
#
## If your project uses WebView with JS, uncomment the following
## and specify the fully qualified class name to the JavaScript interface
## class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
##   public *;
##}
#
## Uncomment this to preserve the line number information for
## debugging stack traces.
-keepattributes SourceFile,LineNumberTable
#
## If you keep the line number information, uncomment this to
## hide the original source file name.
-renamesourcefileattribute SourceFile
## ProGuard rules to avoid issues with records (in proguard-rules.pro)
# Keep MainActivity class
-keep class com.assessment.weatherapplication.view.MainActivity { *; }
# Keep the MyWeatherApplication class
-keep class com.assessment.weatherapplication.di.MyWeatherApplication { *; }
# Keep Hilt-related classes from being removed or obfuscated
-keep class dagger.** { *; }
-dontwarn dagger.**
# proguard-rules.pro
# Prevent obfuscation of BuildConfig class
-keepclassmembers class **.BuildConfig {
    public static final java.lang.String apiKey;
}