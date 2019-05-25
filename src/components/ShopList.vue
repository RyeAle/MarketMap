<template>
  <v-layout
    wrap
    height="100%"
  >
    <v-btn
      absolute
      style="left:50%; bottom: 0%; transform: translate(-50%); width: 100%; height: 5%"
      @click.stop="showNav = !showNav"
    >
      <v-icon dark>list</v-icon>
    </v-btn>

    <v-bottom-nav
      app
      :active.sync="activeBtn"
      :value="showNav"
      absolute
    >
      <v-btn flat color="teal">
        <span>Recents</span>
        <v-icon>history</v-icon>
      </v-btn>
    </v-bottom-nav>
  </v-layout>
</template>

<script>
  export default {
    data () {
      return {
        activeBtn: 1,
        showNav: false
      }
    }
  }
</script>

<style>

</style>
