package com.elpeiretti.ritmodejuego.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.elpeiretti.ritmodejuego.R;
import com.elpeiretti.ritmodejuego.databinding.CustomNumberPickerBinding;

import java.util.function.Consumer;

public class CustomNumberPicker extends LinearLayout {

    private final CustomNumberPickerBinding binding;

    public CustomNumberPicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        binding = CustomNumberPickerBinding.inflate(LayoutInflater.from(context), this, true);

        try (TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomNumberPicker)) {
            binding.label.setText(array.getString(R.styleable.CustomNumberPicker_android_label));
            binding.numberPicker.setMinValue(array.getInt(R.styleable.CustomNumberPicker_android_valueFrom, 0));
            binding.numberPicker.setMaxValue(array.getInt(R.styleable.CustomNumberPicker_android_valueTo, 0));
        }

    }

    public Integer getSelectedNumber() {
        return binding.numberPicker.getValue();
    }

    public void setNumberChangedListener(Consumer<Integer> listener) {
        binding.numberPicker.setOnValueChangedListener((picker, number, i1) -> {
            listener.accept(number);
        });
    }

}
