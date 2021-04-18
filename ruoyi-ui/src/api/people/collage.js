import request from '@/utils/request'

// 查询学院列表
export function listCollage(query) {
  return request({
    url: '/people/collage/list',
    method: 'get',
    params: query
  })
}

// 查询学院详细
export function getCollage(deptId) {
  return request({
    url: '/people/collage/' + deptId,
    method: 'get'
  })
}

// 新增学院
export function addCollage(data) {
  return request({
    url: '/people/collage',
    method: 'post',
    data: data
  })
}

// 修改学院
export function updateCollage(data) {
  return request({
    url: '/people/collage',
    method: 'put',
    data: data
  })
}

// 删除学院
export function delCollage(deptId) {
  return request({
    url: '/people/collage/' + deptId,
    method: 'delete'
  })
}

// 导出学院
export function exportCollage(query) {
  return request({
    url: '/people/collage/export',
    method: 'get',
    params: query
  })
}
//获取学院树
export function treeselect(){
    return request({
      url: '/people/collage/treeselect',
      method:'get'
    })
}
