package com.bankex.pay.di;

import android.content.Context;

import com.bankex.pay.repository.RealmWalletSource;
import com.google.gson.Gson;
import com.bankex.pay.repository.EthereumNetworkRepository;
import com.bankex.pay.repository.EthereumNetworkRepositoryType;
import com.bankex.pay.repository.PreferenceRepositoryType;
import com.bankex.pay.repository.RealmTokenSource;
import com.bankex.pay.repository.SharedPreferenceRepository;
import com.bankex.pay.repository.TokenLocalSource;
import com.bankex.pay.repository.TokenRepository;
import com.bankex.pay.repository.TokenRepositoryType;
import com.bankex.pay.repository.TransactionInMemorySource;
import com.bankex.pay.repository.TransactionLocalSource;
import com.bankex.pay.repository.TransactionRepository;
import com.bankex.pay.repository.TransactionRepositoryType;
import com.bankex.pay.repository.WalletRepository;
import com.bankex.pay.repository.WalletRepositoryType;
import com.bankex.pay.service.AccountKeystoreService;
import com.bankex.pay.service.BlockExplorerClient;
import com.bankex.pay.service.BlockExplorerClientType;
import com.bankex.pay.service.EthplorerTokenService;
import com.bankex.pay.service.GethKeystoreAccountService;
import com.bankex.pay.service.TickerService;
import com.bankex.pay.service.TokenExplorerClientType;
import com.bankex.pay.service.TrustWalletTickerService;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class RepositoriesModule {
	@Singleton
	@Provides
	PreferenceRepositoryType providePreferenceRepository(Context context) {
		return new SharedPreferenceRepository(context);
	}

	@Singleton
	@Provides
	AccountKeystoreService provideAccountKeyStoreService(Context context) {
        File file = new File(context.getFilesDir(), "keystore/keystore");
		return new GethKeystoreAccountService(file);
	}

	@Singleton
    @Provides
    TickerService provideTickerService(OkHttpClient httpClient, Gson gson) {
	    return new TrustWalletTickerService(httpClient, gson);
    }

	@Singleton
	@Provides
	EthereumNetworkRepositoryType provideEthereumNetworkRepository(
            PreferenceRepositoryType preferenceRepository,
            TickerService tickerService) {
		return new EthereumNetworkRepository(preferenceRepository, tickerService);
	}

	@Singleton
	@Provides
    WalletRepositoryType provideWalletRepository(
            OkHttpClient okHttpClient,
			PreferenceRepositoryType preferenceRepositoryType,
			AccountKeystoreService accountKeystoreService,
			EthereumNetworkRepositoryType networkRepository) {
		return new WalletRepository(
		        okHttpClient, preferenceRepositoryType, accountKeystoreService, networkRepository);
	}

	@Singleton
	@Provides
	TransactionRepositoryType provideTransactionRepository(
			EthereumNetworkRepositoryType networkRepository,
			AccountKeystoreService accountKeystoreService,
			BlockExplorerClientType blockExplorerClient) {
		TransactionLocalSource inMemoryCache = new TransactionInMemorySource();
		TransactionLocalSource inDiskCache = null;
		return new TransactionRepository(
				networkRepository,
				accountKeystoreService,
				inMemoryCache,
				inDiskCache,
				blockExplorerClient);
	}

	@Singleton
	@Provides
	BlockExplorerClientType provideBlockExplorerClient(
			OkHttpClient httpClient,
			Gson gson,
			EthereumNetworkRepositoryType ethereumNetworkRepository) {
		return new BlockExplorerClient(httpClient, gson, ethereumNetworkRepository);
	}

	@Singleton
    @Provides
    TokenRepositoryType provideTokenRepository(
            OkHttpClient okHttpClient,
            EthereumNetworkRepositoryType ethereumNetworkRepository,
            TokenExplorerClientType tokenExplorerClientType,
            TokenLocalSource tokenLocalSource) {
	    return new TokenRepository(
	            okHttpClient,
	            ethereumNetworkRepository,
	            tokenExplorerClientType,
                tokenLocalSource);
    }

	@Singleton
    @Provides
    TokenExplorerClientType provideTokenService(OkHttpClient okHttpClient, Gson gson) {
	    return new EthplorerTokenService(okHttpClient, gson);
    }

    @Singleton
    @Provides
    TokenLocalSource provideRealmTokenSource() {
	    return new RealmTokenSource();
    }

    @Singleton
    @Provides
	RealmWalletSource provideRealmWalletSource() {
	    return new RealmWalletSource();
    }
}
