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
        android:id="@+id/to_third_from_first"
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

## 3. Activity에 Fragment 뷰 추가

단일 Activity로 사용할 Activity에 Fragment뷰를 추가한다. 

필수로 name을 설정하고, navGraph와 defaultNavHost 설정을 해야한다.

```xml
<fragment
        android:id="@+id/my_nav_host_fragment"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:defaultNavHost="true"
        app:navGraph="@navigation/nav_graph"/>
```

여기서 나오는 nav_graph는 다음에 설정한다.

## 4. Graph 정의

/res/navigation에 nav_graph.xml파일을 만들어줍니다.

Graph의 기능은 관계를 화면간의 관계를 정의해줍니다.

여기서 startDestination은 시작할 화면을 정해줍니다.

```xml
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools = "http://schemas.android.com/tools"
    android:id="@+id/nav_graph"
    app:startDestination="@+id/first_screen">

    <fragment
        android:id="@+id/first_screen"
        android:name="com.kotlin.jaesungchi.jetpack_navigation.FirstFragment"
        android:label="London screen"
        tools:layout="@layout/fragment_first_screen">
    </fragment>

    <fragment
        android:id="@+id/second_screen"
        android:name="com.kotlin.jaesungchi.jetpack_navigation.SecondFragment"
        android:label="Paris screen"
        tools:layout="@layout/fragment_second_screen">
    </fragment>

    <fragment
        android:id="@+id/third_screen"
        android:name="com.kotlin.jaesungchi.jetpack_navigation.ThirdFragment"
        android:label="Italy screen"
        tools:layout="@layout/fragment_third_screen">
    </fragment>

</navigation>
```

이후 Design 탭을 통해서 각 스크린간 관계를 표시해 줍니다.

![image](https://user-images.githubusercontent.com/37828448/76203500-15b48a00-623a-11ea-889f-9ada48302f63.png)

이제 각 Fragment에서 버튼을 눌렀을 때에 이동하는 코드를 작성합니다.

코드는 Navigation Controller객체를 이용하며 이 Controller는 Host에 보여지고 있는 View를 변경해주는 역할을 합니다.

*FirstFrgment.kt

```kotlin
class FirstFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first_screen,container,false)
        view.findViewById<Button>(R.id.to_second_from_first).setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_first_screen_to_second_screen)
        }
        view.findViewById<Button>(R.id.to_third_from_first).setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_first_screen_to_third_screen)
        }
        return view
    }
}
```

위와 같이 버튼이벤트 코드를 각 Fragment 파일마다 넣어 줍니다.

## 추가1) 애니메이션 추가

화면 전환 애니메이션을 추가하여 효과를 주자.

res/anim 폴더에 각 애니메이션 파일을 추가 해주자.

*slide_in_left.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <translate android:fromXDelta="-100%" android:toXDelta="0%"
        android:fromYDelta="0%" android:toYDelta="0%"
        android:duration="700"/>
</set>
```

*slide_in_right.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <translate android:fromXDelta="100%" android:toXDelta="0%"
        android:fromYDelta="0%" android:toYDelta="0%"
        android:duration="700"/>
</set>
```

*slide_out_left.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <translate android:fromXDelta="0%" android:toXDelta="-100%"
        android:fromYDelta="0%" android:toYDelta="0%"
        android:duration="700"/>
</set>
```

*slide_out_right.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <translate android:fromXDelta="0%" android:toXDelta="100%"
        android:fromYDelta="0%" android:toYDelta="0%"
        android:duration="700"/>
</set>
```

이후 nav_graph.xml 파일에 생긴 Action 함수에 각 이벤트를 넣어 줍니다.

- enterAnim : action 수행시 이동화면에 대한 애니메이션
- exitAnim : action 수행시 현재 화면에 대한 애니매이션
- popEnterAnim : 뒤로가기를 하여 돌아갈때 돌아오는 화면에 대한 애니매이션.
- popExitAnim : 뒤로가기를 하여 돌아갈때 종료되는 화면에 대한 애니매이션.

```xml
<action ... 
        app:popEnterAnim="@anim/slide_in_left"
        app:popExitAnim="@anim/slide_out_right"
        app:enterAnim="@anim/slide_in_right"
        app:exitAnim="@anim/slide_out_left" />
```

## 추가2) Global Action을 활용한 코드 정리

현재 nav_graph.xml 파일에는 각 fragment로 이동하는 액션이 중복되어 있습니다. 이러한 코드를 grobal action을 통해 여러 객체에서 같은 action을 사용하도록 할 수 있습니다.

```xml
<action android:id="@+id/action_global_first_screen"
        app:destination="@id/first_screen"
        app:popEnterAnim="@anim/slide_in_left"
        app:popExitAnim="@anim/slide_out_right"
        app:enterAnim="@anim/slide_in_right"
        app:exitAnim="@anim/slide_out_left"
        />
```

위와 같이 기존 nav_graph파일의 Fragment 내의 action코드를 지우고 각 Fragment로 이동하는 코드를 밖에 작성합니다.

이후 Fragment 파일에 코드를 바꾸어 줍니다.

*FristFragment.kt

```kotlin
class FirstFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first_screen,container,false)
        view.findViewById<Button>(R.id.to_second_from_first).setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_global_second_screen)
        }
        view.findViewById<Button>(R.id.to_third_from_first).setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_global_third_screen)
        }
        return view
    }
}
```

이렇게 Global로 기능을 불러 코드를 정리 할 수 있습니다.