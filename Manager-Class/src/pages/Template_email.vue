<script setup>
import axios from "axios";
import { ref, computed } from "vue";
import { useRoute } from "vue-router";

const route = useRoute();
const idParam = route.params.id;

const getEmailTemplate = ref({});
const findByEmailTemplate = async () => {
    axios
        .get(`http://localhost:8080/api/v1/templates/${idParam}`)
        .then(function (response) {
            getEmailTemplate.value = response.data;
        });
};
findByEmailTemplate();
const getStyledHtmlContent = computed(() => {
    return `<style>${getEmailTemplate.value.cssContent}</style>${getEmailTemplate.value.htmlContent}`;
});
</script>
<template>
    <div>
        <div class="card" style="margin: 15px"></div>
    </div>
</template>
<style scoped></style>
