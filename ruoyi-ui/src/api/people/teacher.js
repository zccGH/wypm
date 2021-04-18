import request from '@/utils/request'

// 查询教师信息列表
export function listTeacher(query) {
  return request({
    url: '/people/teacher/list',
    method: 'get',
    params: query
  })
}

// 查询教师信息详细
export function getTeacher(teacherId) {
  return request({
    url: '/people/teacher/' + teacherId,
    method: 'get'
  })
}

// 新增教师信息
export function addTeacher(data) {
  return request({
    url: '/people/teacher',
    method: 'post',
    data: data
  })
}

// 修改教师信息
export function updateTeacher(data) {
  return request({
    url: '/people/teacher',
    method: 'put',
    data: data
  })
}

// 删除教师信息
export function delTeacher(teacherId) {
  return request({
    url: '/people/teacher/' + teacherId,
    method: 'delete'
  })
}

// 导出教师信息
export function exportTeacher(query) {
  return request({
    url: '/people/teacher/export',
    method: 'get',
    params: query
  })
}
