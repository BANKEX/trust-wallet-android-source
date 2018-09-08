package com.bankex.pay.di;

import com.bankex.pay.ui.OldSettingsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface SettingsModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = {SettingsFragmentModule.class})
    OldSettingsFragment settingsFragment();
}
