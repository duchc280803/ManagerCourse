<script setup>
import { defineProps, defineEmits } from 'vue';

const props = defineProps({
  name: String,
  placeholder: String,
  modelValue: String, // Đổi tên prop từ 'value' thành 'modelValue'
  type: String
});

const emits = defineEmits(['update:modelValue']);
</script>

<template>
    <div class="form-group">
        <input 
            class="form-control" 
            :name="name" 
            :placeholder="placeholder" 
            :value="modelValue" 
            @input="emits('update:modelValue', $event.target.value)"
            :type="type"
        />
    </div>
</template>
