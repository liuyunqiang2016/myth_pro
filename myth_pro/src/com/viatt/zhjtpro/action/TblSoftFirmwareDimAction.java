package com.viatt.zhjtpro.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.viatt.zhjtpro.bo.TblSoftFirmwareDimBo;
import com.viatt.zhjtpro.bo.TblSoftFirmwareDimBo;
import com.viatt.zhjtpro.common.FileHelp;
import com.viatt.zhjtpro.common.GetId;
import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.PageRoll;
import com.viatt.zhjtpro.common.SysLogger;
import com.viatt.zhjtpro.forms.TblSoftDimForm;
import com.viatt.zhjtpro.service.ITblMenuDimService;
import com.viatt.zhjtpro.service.ITblSoftFirmwareDimService;

public class TblSoftFirmwareDimAction extends BaseAction {
	protected ITblSoftFirmwareDimService getTblSoftFirmwareDimService() {
		return (ITblSoftFirmwareDimService) getBean("tblSoftFirmwareDimService");
	}
	protected ITblMenuDimService getTblMenuDimService() {
		return (ITblMenuDimService) getBean("tblMenuDimService");
	}

	public ActionForward proccess(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String method = MyRequest.GetString(request, "method");

		if ("forAddTblSoftDim".equals(method)) {
			return forSaveTblSoftDim(mapping, form, request, response);
		} else if ("addTblSoftDim".equals(method)) {
			return saveTblSoftDim(mapping, form, request, response);
		} else if ("queryTblSoftDim".equals(method)) {
			return queryTblSoftDim(mapping, form, request, response);
		} else if ("removeTblSoftDim".equals(method)) {
			return removeTblSoftDim(mapping, form, request, response);
		} else if ("viewTblSoftDim".equals(method)) {
			return viewTblSoftDim(mapping, form, request, response);
		} else if ("downSoftDim".equals(method)) {
			downSoftDim(mapping, form, request, response);
			return null;
		} else
			return unSpecified(mapping, form, request, response);
	}

	public ActionForward queryTblSoftDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblSoftDimForm TblSoftForm = (TblSoftDimForm) form;
		
		String where="where 1=1 ";
		String strSoftVision = MyRequest.GetString(request, "strSoftVision");
		if(!strSoftVision.equals("")){
			where = where + " and soft_vision like '%" + strSoftVision +"%' ";
		}
		request.setAttribute("strSoftVision", strSoftVision);

		int pageSize = TblSoftForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblSoftDims = getTblSoftFirmwareDimService().findTblSoftFirmwareDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblSoftDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblSoftDims", TblSoftDims);

		return mapping.findForward("querySoftDim");

	}

	public ActionForward forSaveTblSoftDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblSoftDimForm TblSoftForm = (TblSoftDimForm) form;
		boolean isNew = TblSoftForm.getSoftCode() == null
				|| "".equals(TblSoftForm.getSoftCode());
		if (!isNew) {
			TblSoftFirmwareDimBo bo = this.getTblSoftFirmwareDimService().findTblSoftFirmwareDimById(
					TblSoftForm.getSoftCode());
			BeanUtils.copyProperties(TblSoftForm, bo);
		}

		return mapping.findForward("addTblSoftDim");
	}

	public ActionForward saveTblSoftDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblSoftDimForm TblSoftForm = (TblSoftDimForm) form;
		TblSoftFirmwareDimBo bo = new TblSoftFirmwareDimBo();
		boolean isNew = TblSoftForm.getSoftCode() == null
				|| "".equals(TblSoftForm.getSoftCode());

		request.setAttribute("menuPare", TblSoftForm.getMenuPare());
		request.setAttribute("menuChild", TblSoftForm.getMenuChild());
		String strurl = getTblMenuDimService().getMenuString("/softFirmwareDim.do?method=queryTblSoftDim");
		FormFile file = TblSoftForm.getFile();
		String msg = FileHelp.upLoadFile(file, "soft","", file.getFileName());
		if (!msg.equals("")) {
			request.setAttribute("message", msg);
			request.setAttribute("backurl",
					"/softFirmwareDim.do?method=queryTblSoftDim"+strurl);
			return mapping.findForward("FAIL");
		}
		try {
			if (isNew) {
				BeanUtils.copyProperties(bo, TblSoftForm);// 属性拷贝
				if (!file.getFileName().equals("")) {
					bo.setSoftFn(file.getFileName());
					bo.setSoftSize(file.getFileSize() + "");
				}
				bo.setSoftCode(GetId.randomID());
				getTblSoftFirmwareDimService().save(bo);
				SysLogger.logInfo(request, "添加固件信息,固件编号："+bo.getSoftCode());
				request.setAttribute("message", "添加固件信息成功！");
				request.setAttribute("backurl",
						"/softFirmwareDim.do?method=queryTblSoftDim"+strurl);
				return mapping.findForward("SUCCESS");
			} else {
				BeanUtils.copyProperties(bo, TblSoftForm);// 属性拷贝
				if (!file.getFileName().equals("")) {
					bo.setSoftFn(file.getFileName());
					bo.setSoftSize(file.getFileSize() + "");
				}
				getTblSoftFirmwareDimService().save(bo);
				SysLogger.logInfo(request, "修改固件信息,固件编号："+bo.getSoftCode());
				request.setAttribute("message", "修改固件信息成功！");
				request.setAttribute("backurl",
						"/softFirmDim.do?method=queryTblSoftDim"+strurl);
				return mapping.findForward("SUCCESS");
			}
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("backurl",
					"/softFirmwareDim.do?method=queryTblSoftDim"+strurl);
			return mapping.findForward("FAIL");
		}

	}

	public ActionForward removeTblSoftDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String SoftCode = request.getParameter("softCode");
		TblSoftDimForm TblSoftForm = (TblSoftDimForm) form;
		this.getTblSoftFirmwareDimService().removeSoftFirmwareDim(SoftCode);
		String strurl = getTblMenuDimService().getMenuString("/softFirmwareDim.do?method=queryTblSoftDim");
		SysLogger.logInfo(request, "删除固件信息,固件编号："+SoftCode);
		request.setAttribute("message", "删除固件信息成功！");
		request.setAttribute("backurl", "/softFirmwareDim.do?method=queryTblSoftDim"+strurl);
		return mapping.findForward("SUCCESS");
	}

	public ActionForward viewTblSoftDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String SoftCode = request.getParameter("softCode");
		TblSoftDimForm TblSoftForm = (TblSoftDimForm) form;
		TblSoftFirmwareDimBo bo = this.getTblSoftFirmwareDimService().findTblSoftFirmwareDimById(
				SoftCode);
		BeanUtils.copyProperties(TblSoftForm, bo);
		return mapping.findForward("viewTblSoftDim");
	}

	public void downSoftDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String SoftCode = request.getParameter("softCode");
		TblSoftFirmwareDimBo bo = this.getTblSoftFirmwareDimService().findTblSoftFirmwareDimById(
				SoftCode);
		FileHelp.downFile(bo.getSoftFn(),bo.getSoftSize(),"soft",response);
	}

	public ActionForward unSpecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("message", "操作有误，请返回！");
		request.setAttribute("backurl", mapping.findForward("tblSoftDim")
				.getPath());
		return mapping.findForward("FAIL");
	}

}
