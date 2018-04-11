[TOC]

SOD (system of differentiator) of image audit system.

# /image-uploadings
## post: 上传图片
1. 201 and save images to sor
3. save to approve list
4. return to approve list

# /image-uploadings/{iuid}/approvals
## get: 获取审批列表
1. 200 and get

# /image-uploadings/{iuid}/approvals/{type}
## post: 审批
1. 201 and save general approval, return group url
1. 201 and save group approval, return general url



