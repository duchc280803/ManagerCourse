<script setup>
import { defineProps } from 'vue';

const props = defineProps({
    name: String,
    value: String,
    type: String,
    options: Array
});
</script>
<template>
    <select class="form-select" :name="name">
        <option v-for="(option, index) in options" :key="index" :value="option.value">{{ option.label }}</option>
    </select>
</template>