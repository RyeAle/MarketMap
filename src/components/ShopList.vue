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
      height="500px"
      :active.sync="activeBtn"
      :value="showNav"
      absolute
    >
      <v-container grid-list-xl fluid>
        <v-layout column align-space-around justify-start fill-height wrap>
          <v-flex>fefef
            <v-btn flat color="teal">
              <span>Recents</span>
              <v-icon>history</v-icon>
            </v-btn>
          </v-flex>
          <v-flex>fefe
            <v-btn flat color="teal">
              <span>Recents</span>
              <v-icon>history</v-icon>
            </v-btn>
          </v-flex>
          <v-flex>fefe
            <v-btn flat color="teal">
              <span>Recents</span>
              <v-icon>history</v-icon>
            </v-btn>
          </v-flex>
        </v-layout>
      </v-container>
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
