package com.zgg.web.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zgg.web.dao.ZgUserMapper;
import com.zgg.web.pojo.ZgUser;
import com.zgg.web.pojo.ZgUserExample;
import com.zgg.web.pojo.vo.ZgUserVo;
import com.zgg.web.scheduler.redis.handler.RedisHandler;


@Service
//@Transactional(rollbackFor = Exception.class, propagation=Propagation.SUPPORTS, readOnly=true) 查询
//@Transactional(rollbackFor = Exception.class, propagation=Propagation.REQUIRED) 更新
public class ZgUserService {
	@Autowired
	private RedisHandler redisHandler;
	
    @Autowired
    private ZgUserMapper zgUserMapper;

    @GetMapping
	public PageInfo<ZgUser> getZgUserListByParam(ZgUserVo vo) {
		PageHelper.startPage(vo.getPage(), vo.getSize());
		
		ZgUserExample example = new ZgUserExample();
		if (StringUtils.isNotEmpty(vo.getZgUser().getName()))
			example.createCriteria().andNameLike("%"+vo.getZgUser().getName()+"%");
		List<ZgUser> list =  zgUserMapper.selectByExample(example);
        PageInfo<ZgUser> page = new PageInfo<ZgUser>(list);
		return page;
	}

	public ZgUser getZgUserById(Integer id) {
    	ZgUser result = this.zgUserMapper.selectByPrimaryKey(id);
		return result;
	}

	public void updateOrSaveZgUser(ZgUser zgUser) {
		zgUserMapper.insertSelective(zgUser);
	}

	public void delete(Integer id) {
		zgUserMapper.deleteByPrimaryKey(id);
	}


}
