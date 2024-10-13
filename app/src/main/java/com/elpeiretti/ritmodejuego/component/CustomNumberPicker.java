package com.elpeiretti.ritmodejuego.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.elpeiretti.ritmodejuego.R;
import com.elpeiretti.ritmodejuego.databinding.CustomNumberPickerBinding;
import com.elpeiretti.ritmodejuego.util.TextChangedListener;

import java.util.function.Consumer;

public class CustomNumberPicker extends LinearLayout {

    private final CustomNumberPickerBinding binding;

    private String dialogTitle = "Select number";

    private final NumberPicker numberPicker;

    public CustomNumberPicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        binding = CustomNumberPickerBinding.inflate(LayoutInflater.from(context), this, true);

        numberPicker = new NumberPicker(context);

        try (TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomNumberPicker)) {
            binding.label.setText(array.getString(R.styleable.CustomNumberPicker_android_label));
            dialogTitle = array.getString(R.styleable.CustomNumberPicker_title);
            numberPicker.setMinValue(array.getInt(R.styleable.CustomNumberPicker_android_valueFrom, 0));
            numberPicker.setMaxValue(array.getInt(R.styleable.CustomNumberPicker_android_valueTo, 0));
        }

        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(dialogTitle)
                .setView(numberPicker)
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    binding.numberInput.setText(String.valueOf(numberPicker.getValue()));
                })
                .setOnDismissListener(dialogInterface -> {
                    Integer oldNumber = getSelectedNumber();
                    numberPicker.setValue(oldNumber == null ? numberPicker.getMinValue() : oldNumber);
                })
                .create();

        binding.numberInput.setFocusable(false);
        binding.numberInput.setOnClickListener(view -> {
            dialog.show();
        });
    }

    public Integer getSelectedNumber() {
        String number = binding.numberInput.getText().toString();

        return number.isEmpty()
                ? null
                : Integer.valueOf(number);
    }

    public void setNumberChangedListener(Consumer<Integer> listener) {
        binding.numberInput.addTextChangedListener((TextChangedListener) editable ->
                listener.accept(getSelectedNumber()));
    }

}
