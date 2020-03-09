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

## 2. Fragment 생성

총 3개의 프래그먼트를 만들어 1 -> 2 -> 3 -> 1로 순환하는 화면 UI를 만들기 위해 3개의 Frament파일을 생성하여 줍니다.

*FirstFragment.kt

```kotlin
class FirstFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first_screen,container,false)
        return view
    }
}
```

*fragment_first_screen.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".FirstFragment">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="First"
        android:textSize="30sp"
        android:textStyle="bold"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintBottom_toTopOf="@+id/to_second_from_first"/>

    <Button
        android:id="@+id/to_second_from_first"
        style="@style/Widget.AppCompat.Button.Borderless"
        android:layout_width="wrap_content"
        android:layout_height="48dp"
        android:layout_marginStart="8dp"
        android:layout_marginEnd="8dp"
        android:text="Go to Second"
        android:textColor="@color/colorAccent"
        android:textSize="18sp"
        android:textStyle="bold"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"/>

    <Button
        android:id="@+id/to_third_from_one"
        style="@style/Widget.AppCompat.Button.Borderless"
        android:layout_width="wrap_content"
        android:layout_height="48dp"
        android:layout_marginStart="8dp"
        android:layout_marginEnd="8dp"
        android:text="Go to Third"
        android:textColor="@color/colorAccent"
        android:textSize="18sp"
        android:textStyle="bold"
        android:layout_marginTop="30dp"
        app:layout_constraintTop_toBottomOf="@+id/to_second_from_first"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"/>

</androidx.constraintlayout.widget.ConstraintLayout>
```

위와 같은방식으로 Second, Third 파일 및 xml 파일을 생성해줍니다.