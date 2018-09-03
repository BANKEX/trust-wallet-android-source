package com.bankex.pay.repository;

import com.bankex.pay.repository.entity.RealmWallet;
import com.bankex.pay.entity.Wallet;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

public class RealmWalletSource {

    public Completable put(Wallet wallet) {
        return Completable.fromAction(() -> putInNeed(wallet));
    }

    public Single<Wallet> fetch(String address) {
        return Single.fromCallable(() -> {
            Realm realm = null;
            try {
                realm = getRealmInstance();
                RealmResults<RealmWallet> realmItems = realm.where(RealmWallet.class)
                        .sort("addedTime", Sort.ASCENDING)
                        .findAll();
                int len = realmItems.size();
                for (int i = 0; i < len; i++) {
                    RealmWallet realmItem = realmItems.get(i);
                    if (realmItem != null) {
                        return new Wallet(
                                realmItem.getAddress(),
                                realmItem.getName()
                        );
                    }
                }
                return null;
            } finally {
                if (realm != null) {
                    realm.close();
                }
            }
        });
    }

    private Realm getRealmInstance() {
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("wallets" + ".realm")
                .schemaVersion(1)
                .build();
        return Realm.getInstance(config);
    }

    private void putInNeed(Wallet wallet) {
        Realm realm = null;
        try {
            realm = getRealmInstance();
            RealmWallet walletGet = realm.where(RealmWallet.class)
                    .equalTo("address", wallet.address)
                    .findFirst();
            if (walletGet == null) {
                realm.executeTransaction(r -> {
                    RealmWallet obj = r.createObject(RealmWallet.class, wallet.address);
                    obj.setName(wallet.name);
                });
            }
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

}
