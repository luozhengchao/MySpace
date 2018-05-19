package com.jt.sys.service.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.jt.sys.dao.SysUserDao;
import com.jt.sys.entity.SysUser;
//@Service
public class ShiroUserRealm extends AuthorizingRealm {
    
	/*public ShiroUserRealm() {
		HashedCredentialsMatcher credentialsMatcher=
		new HashedCredentialsMatcher();
		credentialsMatcher.setHashAlgorithmName("MD5");
		setCredentialsMatcher(credentialsMatcher);
	}*/
	@Autowired
	private SysUserDao sysUserDao;
	/**实现用户权限数据操作*/
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection arg0) {
		System.out.println("==doGetAuthorizationInfo==");
		System.out.println("arg0=="+arg0);
		//1.获取用户信息
		/*Session session=
	    SecurityUtils.getSubject().getSession();
		String user=
		(String)session.getAttribute("user");*/
		String user=(String)arg0.getPrimaryPrincipal();
		System.out.println("user="+user);
		SysUser sysUser=
		sysUserDao.findUserByUserName(user);
		//2.根据用户信息获取用户权限信息:List<String>
		List<String> list=
		sysUserDao.findUserPermissions(
		sysUser.getId());
		if(list==null)return null;
		Set<String> set=new HashSet<>();
		for(String permission:list){
			if(!StringUtils.isEmpty(permission)){
				set.add(permission);
			}
		}
		//3.封装用户权限信息
		SimpleAuthorizationInfo info=
		new SimpleAuthorizationInfo();
		info.setStringPermissions(set);
		
		return info;//返回给授权管理器，由授权管理器完成权限检测操作
	}
    /**实现用户身份认证数据操作*/
	@Override
	protected AuthenticationInfo 
	 doGetAuthenticationInfo(
	 AuthenticationToken arg0) throws AuthenticationException {
		System.out.println("****AuthenticationInfo****");
		//1.获取token对象
		UsernamePasswordToken token=
		(UsernamePasswordToken)arg0;
		//2.获取用户名
		String userName=token.getUsername();
		//3.根据用户名查询数据库中用户信息
		SysUser user=
		sysUserDao.findUserByUserName(userName);
		//4.对此用户信息进行封装
		SimpleAuthenticationInfo aInfo=
		new SimpleAuthenticationInfo(
			user.getUsername(), 
			user.getPassword(),//已加密的
			ByteSource.Util.bytes(user.getSalt()),//credentialsSalt
			getName());//realmName
		return aInfo;//返回给认证管理器，由认证管理器完成对用户身份信息的认证
	}
	

}
