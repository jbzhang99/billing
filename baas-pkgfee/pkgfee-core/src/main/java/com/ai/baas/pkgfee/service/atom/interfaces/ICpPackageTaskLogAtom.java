package com.ai.baas.pkgfee.service.atom.interfaces;

import com.ai.baas.pkgfee.dao.mapper.bo.CpPackageTaskLog;;

public interface ICpPackageTaskLogAtom {

	public void addCpPackageTaskLog(CpPackageTaskLog log);

	public void addDshmData(CpPackageTaskLog log);
}
