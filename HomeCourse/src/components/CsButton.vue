<script setup>
import { defineProps } from "vue"

const props = defineProps({
    variant: String,
    color: String,
    size: String,
    fullWidth: Boolean,
    active: Boolean,
    icon: String
})

const getClasses = () => {
    let colorValue, sizeValue, fullWidthValue, activeValue; 
    if (props.variant === "gradient") {
        colorValue = `bg-gradient-${props.color}`;
    } else if (props.variant === "outline") {
        colorValue = `btn-outline-${props.color}`;
    } else {
        colorValue = `btn-${props.color}`;
    }

    sizeValue = props.size ? `btn-${props.size}` : null;

    fullWidthValue = props.fullWidth ? `w-100` : null;

    activeValue = props.active ? `active` : null;

    return `${colorValue} ${sizeValue} ${fullWidthValue} ${activeValue}`;
}

const getIcons = (icon) => {
    return icon ? `icon-${icon}` : null;
}
</script>
<template>
    <button type="button" :class="getClasses()">
        <i :class="getIcons(icon)"></i>
    </button>
</template>
