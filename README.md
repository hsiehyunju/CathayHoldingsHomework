# CathayHoldingsHomework
國泰金控 Android Homework

## 架構
- 主要使用 MVVM 架構
- 搭配 Flow 來做資料傳遞
- DI 採用 Koin 方式注入
- 使用 Ktor 來做網路處理
- ApiResponse 採用 Kotlinx.serialization 來做序列化
- 使用 Glide 來做圖片載入

## 特別處理
- Glide 包一層變客製化 View，然後加入 Progress 處理
- 圖片載入失敗時的錯誤顯示
- 使用 Coordinator 處理收合
- Load more data 時顯示 Progress 提醒正在加載

## 遇到問題
- 原先 Image 圖案載不進來，後來才發現 api 回來的 url 是 http 開頭。