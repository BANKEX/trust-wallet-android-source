package com.bankex.pay.repository;

import com.bankex.pay.entity.GasSettings;

public interface PreferenceRepositoryType {
	Boolean getOnBoardingFlag();
	void setCurrentOnBoardingFlag(Boolean flag);

	String getCurrentWalletAddress();
	void setCurrentWalletAddress(String address);

	String getDefaultNetwork();
	void setDefaultNetwork(String netName);

	GasSettings getGasSettings(boolean forTokenTransfer);
	void setGasSettings(GasSettings gasPrice);

}
