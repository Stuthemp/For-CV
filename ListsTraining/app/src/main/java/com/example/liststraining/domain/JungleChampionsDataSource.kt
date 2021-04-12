package com.example.liststraining.domain

import com.example.liststraining.R
import com.example.liststraining.data.models.Champion

class JungleChampionsDataSource {
    fun getChampions(): List<Champion> {
        return listOf(
            Champion("Akali", "https://yt3.ggpht.com/a/AATXAJz1xTwfnByyAru6fb9KtVLXyK4vcVzcqt0ONg=s900-c-k-c0xffffffff-no-rj-mo", false,
                R.raw.akali_select),
            Champion("Ahri", "https://yt3.ggpht.com/a/AATXAJxsJ8M_nKFrMd_RI6S_YK-CofmpIqOxFLTLCIK1=s900-c-k-c0x00ffffff-no-rj", true,
                R.raw.ahri_select),
            Champion("Azir", "https://im0-tub-ru.yandex.net/i?id=c2da417c44643bfefaaecaad71d4c8c4&n=13&exp=1", true,
                R.raw.azir_select),
            Champion("Ezreal", "https://im0-tub-ru.yandex.net/i?id=cfe648249bca280c68f99e1a43468808&n=13&exp=1", true,
                R.raw.ezreal_select),
            Champion("Kha`zix", "https://im0-tub-ru.yandex.net/i?id=13facf99d72cfadbb334eff8ed68dcd4&n=13&exp=1", false,
                R.raw.khazix_select),
            Champion("Graves", "https://yt3.ggpht.com/a/AATXAJz1pHFEOYx4ekVls1JwZ6F2f_JX358Kfseh5ElmpA=s900-c-k-c0xffffffff-no-rj-mo", true,
                R.raw.graves_select),
            Champion("Lulu", "https://yt3.ggpht.com/a/AATXAJxT-v6mbemgwvN88eCqKPCMf-BEfxcgipzgsNzo=s900-c-k-c0xffffffff-no-rj-mo", true,
                R.raw.lulu_select),
            Champion("Maokai", "https://yt3.ggpht.com/a/AATXAJyVluPQDh_FX9_i8KXYuubFshoHmHBDB3JrkhXy=s900-c-k-c0xffffffff-no-rj-mo", false,
                R.raw.maokai_select),
            Champion("Morgana", "https://im0-tub-ru.yandex.net/i?id=80cce56e16b5033cabb753cce7daa99c&n=13&exp=1", true,
                R.raw.morgana_select),
            Champion("Rakan", "https://im0-tub-ru.yandex.net/i?id=183adaf24b647eff2ec126145627da5e&n=13&exp=1", false,
                R.raw.rakan_select),
            Champion("Vel`koz", "https://im0-tub-ru.yandex.net/i?id=9a9b266fac93b793f361a61b24fa55e7&n=13&exp=1", true,
                R.raw.velkoz_select),
            Champion("Senna", "https://im0-tub-ru.yandex.net/i?id=18fd09795b4954b7b34dd56b689576ab&n=13&exp=1", true,
                R.raw.senna_select),
            Champion("Zoe", "https://im0-tub-ru.yandex.net/i?id=621571f46e01cd92d6b3997718c867cd&n=13&exp=1", true,
                R.raw.zoe_select)
        )
    }
}