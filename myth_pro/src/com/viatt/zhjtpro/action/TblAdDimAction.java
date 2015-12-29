package com.viatt.zhjtpro.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.viatt.zhjtpro.bo.TblAdDimBo;
import com.viatt.zhjtpro.bo.TblEquDimBo;
import com.viatt.zhjtpro.bo.TblInfoStatusDimBo;
import com.viatt.zhjtpro.bo.TblUserDimBo;
import com.viatt.zhjtpro.common.FileHelp;
import com.viatt.zhjtpro.common.GetId;
import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.PageRoll;
import com.viatt.zhjtpro.common.SysLogger;
import com.viatt.zhjtpro.forms.TblAdDimForm;
import com.viatt.zhjtpro.service.ITblAdDimService;
import com.viatt.zhjtpro.service.ITblEquDimService;
import com.viatt.zhjtpro.service.ITblInfoStatusDimService;
import com.viatt.zhjtpro.service.ITblMenuDimService;

public class TblAdDimAction extends BaseAction {
	protected ITblAdDimService getTblAdDimService() {
		return (ITblAdDimService) getBean("tblAdDimService");
	}
	
	protected ITblEquDimService getTblEquDimService() {
		return (ITblEquDimService) getBean("tblEquDimService");
	}
	protected ITblMenuDimService getTblMenuDimService() {
		return (ITblMenuDimService) getBean("tblMenuDimService");
	}
	protected ITblInfoStatusDimService getTblInfoStatusDimService() {
		return (ITblInfoStatusDimService) getBean("tblInfoStatusDimService");
	}

	public ActionForward proccess(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String method = MyRequest.GetString(request, "method");

		if ("forAddTblAdDim".equals(method)) {
			return forSaveTblAdDim(mapping, form, request, response);
		} else if ("addTblAdDim".equals(method)) {
			return saveTblAdDim(mapping, form, request, response);
		} else if ("queryTblAdDim".equals(method)) {
			return queryTblAdDim(mapping, form, request, response);
		} else if ("removeTblAdDim".equals(method)) {
			return removeTblAdDim(mapping, form, request, response);
		} else if ("viewTblAdDim".equals(method)) {
			return viewTblAdDim(mapping, form, request, response);
		} else
			return unSpecified(mapping, form, request, response);
	}

	public ActionForward queryTblAdDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblAdDimForm TblAdForm = (TblAdDimForm) form;
		
		String where="where 1=1 ";
		String strTitile = MyRequest.GetString(request, "strTitile");
		if(!strTitile.equals("")){
			where = where + " and titile like '%" + strTitile +"%' ";
		}
		String strCreateDate = MyRequest.GetString(request, "strCreateDate");
		if(!strCreateDate.equals("")){
			where = where + " and create_date like '%" + strCreateDate +"%' ";
		}
		String strEquType = MyRequest.GetString(request, "strEquType");
		if(!strEquType.equals("")){
			where = where + " and equ_type= '" + strEquType +"' ";
		}
		request.setAttribute("strTitile", strTitile);
		request.setAttribute("strCreateDate", strCreateDate);
		request.setAttribute("strEquType", strEquType);

		int pageSize = TblAdForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblAdDims = getTblAdDimService().findTblAdDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblAdDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblAdDims", TblAdDims);

		return mapping.findForward("queryAdDim");

	}

	public ActionForward forSaveTblAdDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblAdDimForm TblAdForm = (TblAdDimForm) form;
		boolean isNew = TblAdForm.getAdId() == null
				|| "".equals(TblAdForm.getAdId());
		if (!isNew) {
			TblAdDimBo bo = this.getTblAdDimService().findTblAdDimById(
					TblAdForm.getAdId());
			BeanUtils.copyProperties(TblAdForm, bo);
		}
		
		return mapping.findForward("addTblAdDim");
	}

	public ActionForward saveTblAdDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblAdDimForm TblAdForm = (TblAdDimForm) form;
		TblAdDimBo bo = new TblAdDimBo();
		boolean isNew = TblAdForm.getAdId() == null
				|| "".equals(TblAdForm.getAdId());
		
		request.setAttribute("menuPare", TblAdForm.getMenuPare());
		request.setAttribute("menuChild", TblAdForm.getMenuChild());
		String strurl = getTblMenuDimService().getMenuString("/adDim.do?method=queryTblAdDim");
		FormFile sfile = TblAdForm.getSfile();  //第一张图片 
		String fileName1Suffix  = "" ;
		if(sfile != null && sfile.getFileName() != "") {
			fileName1Suffix = sfile.getFileName().substring(sfile.getFileName().lastIndexOf(".")).toUpperCase();
		}
        String newFileName = UUID.randomUUID() + fileName1Suffix ;
		String msg = FileHelp.upLoadFile(sfile, "adDim","pic", newFileName);
		if(!msg.equals("")){
			request.setAttribute("message", msg);
			request.setAttribute("backurl",
				"/adDim.do?method=queryTblAdDim"+strurl);
			return mapping.findForward("FAIL");
		}
		
		FormFile bfile = TblAdForm.getBfile();  //第二张图片 
		String	fileName2Suffix  = "" ;
		if (bfile != null && bfile.getFileName() != "") {
			fileName2Suffix = bfile.getFileName().substring(bfile.getFileName().lastIndexOf(".")).toUpperCase() ;
		}
		String newFileName2 = UUID.randomUUID() + fileName2Suffix;
		msg = FileHelp.upLoadFile(bfile, "adDim","pic", newFileName2);
		if(!msg.equals("")){
			request.setAttribute("message", msg);
			request.setAttribute("backurl",
				"/adDim.do?method=queryTblAdDim"+strurl);
			return mapping.findForward("FAIL");
		}
		String createTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		
		try {
			if (isNew) {
				BeanUtils.copyProperties(bo, TblAdForm);// 属性拷贝
				if(!sfile.getFileName().equals("")){
					bo.setSmImg(newFileName);
					bo.setSmSize(sfile.getFileSize()+"");
				}
				if(!bfile.getFileName().equals("")){
					bo.setBgImg(newFileName2 );
					bo.setBgSize(bfile.getFileSize()+"");
				}
				TblUserDimBo user = (TblUserDimBo)request.getSession().getAttribute(TblUserDimBo.USER_INFO_REFERENCE);
				bo.setAdId(GetId.randomID());
				bo.setCreateDate(createTime);
				bo.setCreateUsr(user.getLogName());
				getTblAdDimService().save(bo);
				
				List list = getTblEquDimService().findTblEquDimByWhere(" where equ_type='"+ bo.getEquType()+"'");
				if(list!=null && list.size()>0){
					for(int i=0;i<list.size();i++){
						TblEquDimBo equ = (TblEquDimBo)list.get(i);
						TblInfoStatusDimBo info = new TblInfoStatusDimBo();
						info.setEquCode(equ.getEquCode());
						info.setInfoCode(bo.getAdId());
						info.setInfoType("a");
						info.setSendZt("0");
						info.setUpdType("0");
						info.setInfoTitle(bo.getTitile()) ;
						getTblInfoStatusDimService().save(info);
					}
				}
				
				SysLogger.logInfo(request, "添加广告信息,广告编号："+bo.getAdId());
				request.setAttribute("message", "添加广告信息成功！");
				request.setAttribute("backurl",
						"/adDim.do?method=queryTblAdDim"+strurl);
				return mapping.findForward("SUCCESS");
			} else {
				BeanUtils.copyProperties(bo, TblAdForm);// 属性拷贝
				if(!sfile.getFileName().equals("")){
					bo.setSmImg(newFileName);
					bo.setSmSize(sfile.getFileSize()+"");
				}
				if(!bfile.getFileName().equals("")){
					bo.setBgImg(newFileName2);
					bo.setBgSize(bfile.getFileSize()+"");
				}
				bo.setCreateDate(createTime);
				getTblAdDimService().save(bo);
				
				List list = getTblEquDimService().findTblEquDimByWhere(" where equ_type='"+ bo.getEquType()+"'");
				if(list!=null && list.size()>0){
					for(int i=0;i<list.size();i++){
						TblEquDimBo equ = (TblEquDimBo)list.get(i);
						TblInfoStatusDimBo info = new TblInfoStatusDimBo();
						info.setEquCode(equ.getEquCode());
						info.setInfoCode(bo.getAdId());
						info.setInfoType("a");
						info.setSendZt("0");
						info.setUpdType("1");
						info.setInfoTitle(bo.getTitile()) ;
						getTblInfoStatusDimService().save(info);
					}
				}
				SysLogger.logInfo(request, "修改广告信息,广告编号："+bo.getAdId());
				request.setAttribute("message", "修改广告信息成功！");
				request.setAttribute("backurl",
						"/adDim.do?method=queryTblAdDim"+strurl);
				return mapping.findForward("SUCCESS");
			}
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("backurl",
					"/adDim.do?method=queryTblAdDim"+strurl);
			return mapping.findForward("FAIL");
		}

	}

	public ActionForward removeTblAdDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String AdCode = request.getParameter("adId");
		TblAdDimForm TblAdForm = (TblAdDimForm) form;
		
		TblAdDimBo bo = getTblAdDimService().findTblAdDimById(AdCode);
		this.getTblAdDimService().removeAdDim(AdCode);
		
		//删除信息，同时删除信息发送表tbl_infostatus_dim的新增修改
		getTblInfoStatusDimService().deleteList(" where info_code='"+bo.getAdId()+"' and info_type='a'");
		
		String strurl = getTblMenuDimService().getMenuString("/adDim.do?method=queryTblAdDim");
		List list = getTblEquDimService().findTblEquDimByWhere(" where equ_type='"+ bo.getEquType()+"'");
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				TblEquDimBo equ = (TblEquDimBo)list.get(i);
				TblInfoStatusDimBo info = new TblInfoStatusDimBo();
				info.setEquCode(equ.getEquCode());
				info.setInfoCode(bo.getAdId());
				info.setInfoType("a");
				info.setSendZt("0");
				info.setUpdType("2");
				getTblInfoStatusDimService().save(info);
			}
		}
		SysLogger.logInfo(request, "删除广告信息,广告编号："+AdCode);
		request.setAttribute("message", "删除广告信息成功！");
		request.setAttribute("backurl",
				"/adDim.do?method=queryTblAdDim"+strurl);
		return mapping.findForward("SUCCESS");
	}
	
	public ActionForward viewTblAdDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String AdCode = request.getParameter("adId");
		TblAdDimForm TblAdForm = (TblAdDimForm) form;
		TblAdDimBo bo = this.getTblAdDimService().findTblAdDimById(AdCode);
		if(bo==null){
			String strurl = getTblMenuDimService().getMenuString("/adDim.do?method=queryTblAdDim");
			request.setAttribute("message", "该广告信息已删除");
			request.setAttribute("backurl",
					"/adDim.do?method=queryTblAdDim"+strurl);
			return mapping.findForward("FAIL");
		}
		BeanUtils.copyProperties(TblAdForm, bo);
		return mapping.findForward("viewTblAdDim");
	}

	public ActionForward unSpecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("message", "操作有误，请返回！");
		request.setAttribute("backurl", mapping.findForward("tblAdDim")
				.getPath());
		return mapping.findForward("FAIL");
	}

}
