package com.entor.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.entor.dao.UserDao;
import com.entor.entity.User;

import com.entor.service.UserService;

@Controller
@RequestMapping("/User")
public class UserController {

	@Resource
	private UserService UserService;
	
	private User User;
	private HttpServletRequest request;
	private HttpServletResponse response;
	@RequestMapping("/add")
	public void add(User User) {
		JSONObject jo = new JSONObject();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			UserService.add(User);
			jo.put("state", 0);
			jo.put("msg", "ĞÂÔö³É¹¦");
		}catch(Exception e) {
			jo.put("state", -1);
			jo.put("msg", "ĞÂÔöÊ§°Ü"+e.getMessage());
		}finally {
			String str = JSON.toJSONString(jo);
			System.out.println(str);
			out.write(str);
			out.flush();
			out.close();
		}
	}
	@RequestMapping("/deleteMore")
	public void deleteMore(String ids) {
		JSONObject jo = new JSONObject();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			UserService.deleteMore(User.class,ids);
			jo.put("state", 0);
			jo.put("msg", "É¾³ı³É¹¦");
		}catch(Exception e) {
			jo.put("state", -1);
			jo.put("msg", "É¾³ıÊ§°Ü"+e.getMessage());
		}finally {
			String str = JSON.toJSONString(jo);
			System.out.println(str);
			out.write(str);
			out.flush();
			out.close();
		}
	}
	@RequestMapping("/update")
	public void update(User User) {
		JSONObject jo = new JSONObject();
		PrintWriter out = null;
		try {
			out = response.getWriter();

			UserService.update(User);
			jo.put("state", 0);
			jo.put("msg", "ĞŞ¸Ä³É¹¦");
		}catch(Exception e) {
			jo.put("state", -1);
			jo.put("msg", "ĞŞ¸ÄÊ§°Ü"+e.getMessage());
		}finally {
			String str = JSON.toJSONString(jo);
			System.out.println(str);
			out.write(str);
			out.flush();
			out.close();
		}
	}
	@RequestMapping("/queryByPage")
	public void queryByPage(String page) {
		
		String qname = request.getParameter("qname");
		String qUsername = request.getParameter("qUsername");
		String qsex = request.getParameter("qsex");
		String qbeginDate = request.getParameter("qbeginDate");
		String qendDate = request.getParameter("qendDate");
		//é‘¾å³°å½‡å§£å¿›ã?‰é„å‰§ãšç’æ¿ç¶éï¿?
		String rows = request.getParameter("rows");
	
		String condition = " where 1=1 ";
		if(qname!=null&&!qname.equals("")&&!qname.equalsIgnoreCase("null")){
			condition += " and name like '%"+qname+"%'";
		}
		if(qUsername!=null&&!qUsername.equals("")&&!qname.equalsIgnoreCase("null")){
			condition += " and Username like '%"+qUsername+"%'";
		}
		if(qsex!=null&&!qsex.equals("")&&!qsex.equals("-1")&&!qname.equalsIgnoreCase("null")){
			condition += " and sex = "+qsex;
		}
		if(qbeginDate!=null&&!qbeginDate.equals("")) {
			condition += " and birthday >= '"+qbeginDate+"'";
		}
		if(qendDate!=null&&!qendDate.equals("")) {
			condition += " and birthday <= '"+qendDate+"'";
		}

		//è¤°æ’³å¢ æ¤¤ï¿?
		int sp = 1;
		//é¬æ˜î†‡è¤°æ›ŸæšŸ
		int totals = UserService.getTotals(User.class);
		//å§£å¿›ã€‰ç’æ¿ç¶éï¿½
		int pageSize = Integer.parseInt(rows);
		//é¬å©šã€‰éï¿?
		int pageCounts = totals/pageSize;
		if(totals%pageSize!=0){
			pageCounts++;
		}
		try{
			sp = Integer.parseInt(page);
		}catch(Exception e){
			sp = 1;
		}
		if(sp>pageCounts){
			sp = pageCounts;
		}
		if(sp<1){
			sp = 1;
		}
		List<User> list =UserService.queryByPage(User.class, sp, pageSize);
		try {
			PrintWriter out = response.getWriter();
			JSONObject jo = new JSONObject();
			jo.put("total", totals);
			jo.put("rows", list);
			String json = JSON.toJSONString(jo);
			System.out.println(json);
			out.write(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*@RequestMapping("/login")
	public String login()throws Exception{
		User s = UserService.login(User.getName(), User.getPassword());
		if(s!=null) {
			request.getSession().setAttribute("User", s);
			return "success.jsp";
		}else {
			request.setAttribute("msg", "µÇÂ¼Ê§°Ü");
			return "login.jsp";
		}
	}*/
//	@RequestMapping("/saveRole")
//	public void saveRole(String uids,String rids) {
//		List<UserRole> list = new ArrayList<>();
//		UserRoleDao.deleteUserRolesByUids(uids);
//		for(String uid:uids.split(",")) {
//			for(String rid:rids.split(",")) {
//				UserRole UserRole = new UserRole();
//				UserRole.setUid(Integer.parseInt(uid));
//				UserRole.setRid(Integer.parseInt(rid));
//				list.add(UserRole);
//			}
//		}JSONObject jo = new JSONObject();
//		PrintWriter out = null;
//		try {
//			out = response.getWriter();
//			addMore(list);
//			jo.put("state", 0);
//			jo.put("msg", "åˆ†é…æˆåŠŸ");
//		}catch(Exception e) {
//			jo.put("state", -1);
//			jo.put("msg", "åˆ†é…å¤±è´¥"+e.getMessage());
//		}finally {
//			String str = JSON.toJSONString(jo);
//			System.out.println(str);
//			out.write(str);
//			out.flush();
//			out.close();
//		}
//		
//	}
//	@RequestMapping("/getOwnerRoles")
//	public void getOwnerRoles(String uids) {
//		try {
//			PrintWriter out = response.getWriter();
//			RoleDao dao = new RoleDaoImpl();
//			List<Role> list = dao.queryAllRolesByUids(uids);
//			String str = JSON.toJSONString(list);
//			System.out.println(str);
//			out.write(str);
//			out.flush();
//			out.close();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	/*@RequestMapping("/queryAll")
	public void queryAll() {
		List<User>list=UserService.queryAll(User.class);
		try {
			PrintWriter out = response.getWriter();
			String json = JSON.toJSONString(list);
			System.out.println(json);
			out.write(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	/**
	 * æ¾¶å‹­æ‚Šé™å‚›æšŸæ¶“çƒ˜æ£©éˆç†¸ç‰¸å¯®ï¿½
	 * @param binder
	 */
	@InitBinder
    public void initBinder(ServletRequestDataBinder binder){
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
	@ModelAttribute
	public void setRequestAndResponse(HttpServletRequest request,HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.response.setContentType("text/html;charset=utf-8");
	}
}
