package com.bankex.pay.repository;

import com.bankex.pay.entity.NetworkInfo;

public interface OnNetworkChangeListener {
	void onNetworkChanged(NetworkInfo networkInfo);
}
