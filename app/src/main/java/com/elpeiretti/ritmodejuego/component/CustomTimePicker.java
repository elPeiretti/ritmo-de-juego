package com.elpeiretti.ritmodejuego.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.elpeiretti.ritmodejuego.R;
import com.elpeiretti.ritmodejuego.databinding.CustomTimePickerBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class CustomTimePicker extends LinearLayout {

    private final CustomTimePickerBinding binding;
    private final List<BiConsumer<Integer, Integer>> timeChangedListeners = new ArrayList<>();

    public CustomTimePicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        binding = CustomTimePickerBinding.inflate(LayoutInflater.from(context), this, true);

        try (TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomTimePicker)) {
            binding.label.setText(array.getString(R.styleable.CustomTimePicker_android_label));
        }

        binding.timeInput.addTextChangedListener(getTextWatcher());

    }

    public void addTimeChangedListener(BiConsumer<Integer, Integer> listener) {
        timeChangedListeners.add(listener);
    }

    public Integer getHour() {
        String text = binding.timeInput.getText().toString();
        if (text.isEmpty() || text.length() < 3)
            return null;
        return Integer.valueOf(text.substring(0,2));
    }

    public Integer getMinute() {
        String text = binding.timeInput.getText().toString();
        if (text.isEmpty() || text.length() < 5)
            return null;
        return Integer.valueOf(text.substring(3,5));
    }

    public void clear() {
        binding.timeInput.setText("");
    }

    private TextWatcher getTextWatcher() {
        return new TextWatcher(){

            boolean deleted = false;
            boolean edited = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                deleted = count == 0;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                int length = editable.length();
                if (length == 0 || edited) return;
                char added = editable.charAt(length-1);

                if (added < '0' || added > '9') {
                    editable.delete(length-1, length);
                }
                else if (length == 1 && added > '2') {
                    withoutTriggering(() -> {
                        editable.clear();
                        editable.append(String.format("0%c:",added));
                    });
                }
                else if (length == 2) {
                    if (!deleted)
                        withoutTriggering(() -> editable.append(':'));
                    else
                        editable.delete(length-1, length);
                }
                else if (length == 4 && added > '5') {
                    withoutTriggering(() -> {
                        editable.delete(length-1, length);
                        editable.append(String.format("0%c",added));
                    });
                }
                else if (length > 5) {
                    withoutTriggering(() -> editable.delete(length-1, length));
                    return;
                }
                timeChangedListeners.forEach(l -> l.accept(getHour(), getMinute()));
            }

            private void withoutTriggering(Runnable r) {
                edited = true;
                r.run();
                edited = false;
            }
        };
    }
}
