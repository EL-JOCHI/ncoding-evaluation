<template>
  <q-layout view="lHh Lpr lff" >
    <q-header elevated class="bg-cyan-8">
      <q-toolbar class="bg-primary text-white">
        <q-toolbar-title>Students Administration Panel</q-toolbar-title>
        <q-btn flat @click="drawer = !drawer" round dense icon="menu"/>
      </q-toolbar>
    </q-header>

    <q-drawer
      v-model="drawer"
      show-if-above
      :width="300"
      :breakpoint="400"
    >
      <q-scroll-area class="scroll-area">
        <q-list padding>
          <q-item clickable v-ripple to="/" active-class="active-menu-item" exact>
            <q-item-section avatar>
              <q-icon name="people"/>
            </q-item-section>

            <q-item-section>
              Students
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple to="courses" active-class="active-menu-item">
            <q-item-section avatar>
              <q-icon name="topic"/>
            </q-item-section>

            <q-item-section>
              Courses
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple to="about" active-class="active-menu-item">
            <q-item-section avatar>
              <q-icon name="support"/>
            </q-item-section>

            <q-item-section>
              About Me
            </q-item-section>
          </q-item>
        </q-list>
      </q-scroll-area>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script lang="ts">
import { ref } from 'vue'

export default {
  name: 'UserLayout',
  setup () {
    const drawer = ref(false);
    return {
      drawer
    }
  }
}
</script>
<style lang="sass">
.scroll-area
  height: calc(100% - 150px)
  margin-top: 150px
  border-right: 1px solid $primary
.active-menu-item
  color: white
  background: $secondary
</style>
