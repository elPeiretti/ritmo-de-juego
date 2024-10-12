package com.elpeiretti.ritmodejuego.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.elpeiretti.ritmodejuego.R;
import com.elpeiretti.ritmodejuego.databinding.LabeledEditTextBinding;
import com.elpeiretti.ritmodejuego.util.TextChangedListener;

public class LabeledEditText extends LinearLayout {

    private LabeledEditTextBinding binding;

    public LabeledEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        binding = LabeledEditTextBinding.inflate(LayoutInflater.from(context), this, true);

        try (TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LabeledEditText)) {
            binding.label.setText(array.getString(R.styleable.LabeledEditText_android_label));
            binding.input.setInputType(array.getInt(R.styleable.LabeledEditText_android_inputType, InputType.TYPE_CLASS_NUMBER));
        }
    }

    public void setText(CharSequence text) {
        binding.input.setText(text);
    }

    public void addTextChangedListener(TextChangedListener listener) {
        binding.input.addTextChangedListener(listener);
    }
}
