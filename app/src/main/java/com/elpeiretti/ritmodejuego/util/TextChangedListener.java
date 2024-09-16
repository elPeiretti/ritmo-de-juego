package com.elpeiretti.ritmodejuego.util;

import android.text.TextWatcher;

public interface TextChangedListener extends TextWatcher {
    @Override
    default void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
    @Override
    default void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
}