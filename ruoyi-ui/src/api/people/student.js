import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi";

// 查询学生列表
export function listStudent(query) {
  return request({
    url: '/people/student/list',
    method: 'get',
    params: query
  })
}

// 查询学生详细
export function getStudent(id) {
  return request({
    url: '/people/student/' + praseStrEmpty(id),
    method: 'get'
  })
}

// 新增学生
export function addStudent(data) {
  return request({
    url: '/people/student/',
    method: 'post',
    data: data
  })
}

// 修改学生
export function updateStudent(data) {
  return request({
    url: '/people/student',
    method: 'put',
    data: data
  })
}

// 删除学生
export function delStudent(id) {
  return request({
    url: '/people/student/' + id,
    method: 'delete'
  })
}

// 导出学生
export function exportStudent(query) {
  return request({
    url: '/people/student/export',
    method: 'get',
    params: query
  })
}

// 学生密码重置
export function resetUserPwd(userId, password) {
  const data = {
    userId,
    password
  }
  return request({
    url: '/system/user/resetPwd',
    method: 'put',
    data: data
  })
}

// 学生状态修改
export function changeUserStatus(userId, status) {
  const data = {
    userId,
    status
  }
  return request({
    url: '/system/user/changeStatus',
    method: 'put',
    data: data
  })
}

// 查询学生个人信息
export function getUserProfile() {
  return request({
    url: '/system/user/profile',
    method: 'get'
  })
}

// 修改学生个人信息
export function updateUserProfile(data) {
  return request({
    url: '/system/user/profile',
    method: 'put',
    data: data
  })
}

// 学生密码重置
export function updateUserPwd(oldPassword, newPassword) {
  const data = {
    oldPassword,
    newPassword
  }
  return request({
    url: '/system/user/profile/updatePwd',
    method: 'put',
    params: data
  })
}

// 学生头像上传
export function uploadAvatar(data) {
  return request({
    url: '/system/user/profile/avatar',
    method: 'post',
    data: data
  })
}

// 下载学生导入模板
export function importTemplate() {
  return request({
    url: '/people/student/importTemplate',
    method: 'get'
  })
}
