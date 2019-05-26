<template>
  <v-layout
    wrap
    height="100%"
  >
    <v-btn
      id="showupBtn"
      absolute
      style="left:50%; bottom: 0; transform: translate(-50%); width: 100%; height: 8%;
      background-color: white"
      @click.stop="toggle"
      class="text-xs-center pb-2 pt-2"
    >

      <v-flex><div
        style="background-color: black; width: 40px; height: 3px; border-radius: 3px;
        margin:auto; margin-bottom: 4px; margin-bottom: 3px;"></div>Ближайшие магазины</v-flex>
    </v-btn>
    <div class="shops" style="position: absolute; bottom: 0;
    left: 0;
    overflow: scroll;
    background-color: white">

      <v-container fluid class="pt-0">
        <v-layout column align-space-around fill-height>
            <list-row v-for="shop in shops" :key=shop.id :shop="shop" />
        </v-layout>
      </v-container>
    </div>
  </v-layout>
</template>

<script>
  import ListRow from './ListRow.vue'

  export default {
    props: ['shops'],
    components: {ListRow},
    data:() => ({
        showNav: false
    }),
    methods: {
      showCollapse() {
        document.querySelectorAll(".shops")[0].style['height'] = '50%';
        document.querySelector('#showupBtn').style['bottom'] = '50%';
        this.showNav = true;
      },
      hideCollapse() {
        if (this.showNav) {
          document.querySelectorAll(".shops")[0].style['height'] = '0';
          document.querySelector('#showupBtn').style['bottom'] = '0';
          this.showNav = false;
        }
      },
      toggle() {
        this.showNav ? this.hideCollapse() : this.showCollapse();
      }
    }
  }
</script>

<style>
  .shops {
    padding: 0 18px;
    height: 0;
    width: 100%;
    text-align: center;
    overflow: hidden;
    transition: height 0.2s ease-out;
    background-color: #f1f1f1;
  }
</style>
