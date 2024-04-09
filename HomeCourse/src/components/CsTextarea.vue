<script setup>
    import { defineProps } from "vue";

    const props = defineProps({
        name: String,
        placeholder: String,
        name: String,
        value: String
    })
</script>
<template>
    <div class="form-group">
        <textarea 
            class="form-control" 
            :placeholder="placeholder" 
            :name="name" 
            :value="value"
        >
        </textarea>
    </div>
</template>