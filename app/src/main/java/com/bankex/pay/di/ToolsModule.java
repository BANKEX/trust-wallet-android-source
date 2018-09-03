package com.bankex.pay.di;

import android.content.Context;

import com.google.gson.Gson;
import com.bankex.pay.App;
import com.bankex.pay.repository.PasswordStore;
import com.bankex.pay.repository.TrustPasswordStore;
import com.bankex.pay.util.LogInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
class ToolsModule {
	@Provides
	Context provideContext(App application) {
		return application.getApplicationContext();
	}

	@Singleton
	@Provides
	Gson provideGson() {
		return new Gson();
	}

	@Singleton
	@Provides
	OkHttpClient okHttpClient() {
		return new OkHttpClient.Builder()
                .addInterceptor(new LogInterceptor())
                .build();
	}

	@Singleton
	@Provides
	PasswordStore passwordStore(Context context) {
		return new TrustPasswordStore(context);
	}
}
