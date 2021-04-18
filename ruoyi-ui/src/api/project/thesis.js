import request from '@/utils/request'

// 查询论文相关信息列表
export function listThesis(query) {
  return request({
    url: '/project/thesis/list',
    method: 'get',
    params: query
  })
}

// 查询论文相关信息详细
export function getThesis(id) {
  return request({
    url: '/project/thesis/' + id,
    method: 'get'
  })
}

// 新增论文相关信息
export function addThesis(data) {
  return request({
    url: '/project/thesis',
    method: 'post',
    data: data
  })
}

// 修改论文相关信息
export function updateThesis(data) {
  return request({
    url: '/project/thesis',
    method: 'put',
    data: data
  })
}

// 删除论文相关信息
export function delThesis(id) {
  return request({
    url: '/project/thesis/' + id,
    method: 'delete'
  })
}

// 导出论文相关信息
export function exportThesis(query) {
  return request({
    url: '/project/thesis/export',
    method: 'get',
    params: query
  })
}
