package org.iikun.iik.ui.ModalDrawer

import org.jetbrains.compose.resources.DrawableResource

/**
 * @creator: ii_kun
 * @email: weijikun1@icloud.com
 * @time 2024/10/25 17:34
 */
data class UserInfoModel (
    val headPortrait: DrawableResource, // 头像
    val name: String, // 名称
    val badge: String, // 徽章
    val account: String, // 账号余额
    val age: String, // 树龄
    val grade: String, // 等级
    val experience: String, // 经验
    val concern: String, // 关注
    val AfanOfSomeone: String, // 粉丝
    val collection: String // 收藏
)