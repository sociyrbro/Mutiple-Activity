# 实验三 多 Activity

Android 移动应用开发课程实验。项目使用 Kotlin 与 Jetpack Compose，实现 1 个主 Activity 和 4 个二级 Activity。

## 功能

- MainActivity 通过显式 Intent 启动 ForwardActivity，并正向传递姓名和学号。
- InputReturnActivity 接收输入内容，通过 `setResult` 返回主页面。
- CameraActivity 使用 `ACTION_IMAGE_CAPTURE` 隐式启动系统相机，并显示拍摄结果。
- StopwatchActivity 实现开始、暂停、重置和百分秒显示，旋转屏幕后计时连续。
- 全部界面文字通过 `strings.xml` 管理。

## 开发环境

- Kotlin 2.2.10
- Jetpack Compose
- compileSdk 37
- minSdk 24
- Gradle 9.5

## 作者

郭理杰 2024110213
