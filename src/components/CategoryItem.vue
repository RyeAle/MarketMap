<template>
  <v-flex>
    <v-card class="text-xs-center font-weight-bold" @click="this.func">
      <v-card-text>{{name}}</v-card-text>
      <v-responsive>
        <v-img :src="'http://192.168.43.95:8080/'+this.imgUrl"
               style="margin: auto; width: 40%" class="mb-3"></v-img>
      </v-responsive>
    </v-card>
  </v-flex>
</template>
<script>
  import {setAttrs} from '../api/svg';

  export default {
    data: () => ({
      func: () => {
      }
    }),
    props: ['imgUrl', 'name', 'toggleDialog', 'cbb', 'doc'],
    mounted() {
      this.func = () => {
        const img = document.createElementNS('http://www.w3.org/2000/svg', 'image');
        img.setAttributeNS('http://www.w3.org/1999/xlink', 'xlink:href',
          "http://192.168.43.95:8080"
          + this.imgUrl);
        setAttrs(img, 'width', 100, 'height', 100, 'x', this.cbb.x.baseVal.value, 'y',
          this.cbb.y.baseVal.value, 'id', `shelf:${this.cbb.id}`, 'class',
          'shelf');
        document.getElementById('svg').append(img);
        this.toggleDialog();
      }
    }
  }
</script>
<style scoped>
  /*div.root {*/
  /*display: flex;*/
  /*flex-direction: row;*/
  /*justify-content: space-between;*/
  /*background: #2a333a;*/
  /*padding: 0;*/
  /*}*/

  img {
    display: block;
  }
</style>
