package com.elpeiretti.ritmodejuego.component;

import android.content.Context;
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

import java.util.Locale;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class CustomTimeInput extends LinearLayout {

    public static final int DEFAULT_HOUR = 0;
    public static final int DEFAULT_MINUTE = 15;

    private final CustomTimePickerBinding binding;

    private MaterialTimePicker timePicker;
    private Integer hour = DEFAULT_HOUR;
    private Integer minute = DEFAULT_MINUTE;
    private Supplier<FragmentManager> fragmentManagerSupplier;

    public CustomTimeInput(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        binding = CustomTimePickerBinding.inflate(LayoutInflater.from(context), this, true);

        binding.label.setText(getResources().getText(R.string.tiempo_label));

        createTimePicker();
        binding.timeInput.setOnClickListener(view -> {
            if (fragmentManagerSupplier == null) {
                Log.w("CustomTimeInput","FragmentManagerSupplier NOT SET!");
                return;
            }
            timePicker.show(fragmentManagerSupplier.get(), "timePicker");
        });
        binding.timeInput.setFocusable(false);
        updateInputText();
    }

    private void createTimePicker() {
        timePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setInputMode(MaterialTimePicker.INPUT_MODE_KEYBOARD)
                .setHour(hour)
                .setMinute(minute)
                .build();

        timePicker.addOnPositiveButtonClickListener(view -> {
            hour = timePicker.getHour();
            minute = timePicker.getMinute();
            updateInputText();
        });
    }

    public void setFragmentManagerSupplier(Supplier<FragmentManager> supplier) {
        this.fragmentManagerSupplier = supplier;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setTime(int horas, int minutos) {
        hour = horas;
        minute = minutos;
        updateInputText();
    }

    public void addTimeChangedListener(BiConsumer<Integer, Integer> listener) {
        timePicker.addOnPositiveButtonClickListener(view ->
            listener.accept(hour, minute));
    }

    private void updateInputText() {
        binding.timeInput.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
    }
}
