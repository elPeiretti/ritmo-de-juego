package com.elpeiretti.ritmodejuego.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.elpeiretti.ritmodejuego.R;
import com.elpeiretti.ritmodejuego.databinding.CustomTimePickerBinding;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.time.LocalTime;
import java.util.Locale;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class CustomTimePicker extends LinearLayout {

    private final CustomTimePickerBinding binding;

    private MaterialTimePicker timePicker;
    private final String pickerTitle;
    private Supplier<FragmentManager> fragmentManagerSupplier;

    private Integer hour = LocalTime.now().getHour();
    private Integer minute = LocalTime.now().getMinute();

    public CustomTimePicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        binding = CustomTimePickerBinding.inflate(LayoutInflater.from(context), this, true);

        try (TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomTimePicker)) {
            binding.label.setText(array.getString(R.styleable.CustomTimePicker_android_label));
            pickerTitle = array.getString(R.styleable.CustomTimePicker_title);
        }

        createTimePicker();
        binding.timeInput.setOnClickListener(view -> {
            if (fragmentManagerSupplier == null) {
                Log.w("CustomTimePicker","FragmentManagerSupplier NOT SET!");
                return;
            }
            timePicker.show(fragmentManagerSupplier.get(), "timePicker");
        });
        binding.timeInput.setFocusable(false);

    }

    private void createTimePicker() {
        timePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
                .setHour(hour)
                .setMinute(minute)
                .setTitleText(pickerTitle)
                .build();

        timePicker.addOnPositiveButtonClickListener(view -> {
            hour = timePicker.getHour();
            minute = timePicker.getMinute();
            binding.timeInput.setText(String.format(Locale.getDefault(), "%02d:%02d", timePicker.getHour(), timePicker.getMinute()));
        });
    }

    public void setFragmentManagerSupplier(Supplier<FragmentManager> supplier) {
        this.fragmentManagerSupplier = supplier;
    }

    public void addTimeChangedListener(BiConsumer<Integer, Integer> listener) {
        timePicker.addOnPositiveButtonClickListener(view ->
                listener.accept(timePicker.getHour(), timePicker.getMinute()));
    }

    public Integer getHour() {
        if (binding.timeInput.getText().toString().isEmpty())
            return null;
        return hour;
    }

    public Integer getMinute() {
        if (binding.timeInput.getText().toString().isEmpty())
            return null;
        return minute;
    }
}
