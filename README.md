# Jetpack-Navigation-Example

![imge](https://img.shields.io/badge/ProjectType-SingleStudy-green) ![imge](https://img.shields.io/badge/Language-Kotlin-yellow) ![imge](https://img.shields.io/badge/Tools-AndroidStudio-blue)

안드로이드 스튜디오의 손쉬운 UI 변경을 위한 JetPack의 Navigation 이용 예제입니다.

기존의 여러개의 액티비티로 이루어져 intent등을 통해 데이터를 주고받거나 액티비티 이동간 생명주기를 신경써야 하는 부분을 Single 액티비티 + ViewModel 구조를 통해 화면 전환 및 데이터 송수신 문제를 해결할 수 있게 되었다.

## 1. Dependencies 설정

앱단의 gradle에 dependencies 설정을 한다.

```kotlin
dependencies{
	def nav_version = "1.0.0-alpha07"
	implementation "android.arch.navigation:navigation-fragment:$nav_version"
    implementation "android.arch.navigation:navigation-ui-ktx:$nav_version"
}
```