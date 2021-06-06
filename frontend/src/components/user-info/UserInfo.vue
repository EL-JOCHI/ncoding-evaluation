<template>
  <q-img class="absolute-top" src="https://cdn.quasar.dev/img/material.png" style="height: 150px">
    <div class="absolute-bottom bg-transparent">
      <q-avatar size="56px" class="q-mb-sm">
        <img src="https://media-exp1.licdn.com/dms/image/C4E03AQGtmokkVSGNSw/profile-displayphoto-shrink_400_400/0/1517075147256?e=1628121600&v=beta&t=Q23jSeByO5O-Avyj0HiDh4bTbG6Kxzlt6tJPkxfiDdY">
      </q-avatar>
      <div class="text-weight-bold">Miguel Hurtado</div>
      <div>@miguelalejandrohurtadoorellana</div>
    </div>
  </q-img>
</template>

<script>
import {defineComponent} from 'vue';

export default defineComponent({
  name: 'UserInfo'
})
</script>

<style scoped>
</style>
