<template>
  <router-view />
</template>
<script lang="ts">
import { defineComponent, onMounted } from 'vue';
import { useStore } from 'vuex';

export default defineComponent({
  name: 'App',
  setup() {
    const store = useStore();
    onMounted(() => {
      store.dispatch('student/fetchStudentData').catch(() => {
        console.log("Error");
      });
    });
  }
})
</script>
