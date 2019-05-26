<template>
  <div>
    <h4>{{stages[currentStage].header}}</h4>
    <div id="svgmap" class="content"></div>
    <!--<v-dialog v-model="dialog" width="90%"/>-->
    <button
      @click="goNextStep"
      style="position: absolute; bottom: 0; background: #666; width: 100px; height: 50px;">
      Go next
    </button>
    <button
      v-if="currentStage !== 0"
      @click="goPrevStep"
      style="position: absolute; bottom: 0; left: 20px; background: #666; width: 100px; height: 50px;">
      Go prev
    </button>
  </div>
</template>

<script>
  import { createGrid } from '../api/svg';
  import interact from 'interactjs';
  import axios from 'axios';

  export default {
    props: {
      mapWidth: Number,
      mapHeight: Number,
    },
    data: () => ({
      currentStage: 0,
      showCategoryPopup: false,
      showProductsPopup: false,
      stages: [
        {
          order: 0,
          stage: 'shelf',
          color: '#F9EAC0',
          header: 'Отметьте существующие стеллажи с товарами на сетке'
        },
        {
          order: 1,
          stage: 'shelfCategory',
          header: 'Обозначьте секции с продуктами'
        },
        {
          order: 2,
          stage: 'pass',
          color: '#EBF4FC',
          header: 'Обозначьте пути на карте'
        },
        {
          order: 3,
          stage: 'entry',
          color: '#515151',
          header: 'Укажите вход в магазин'
        },
        {
          order: 4,
          stage: 'cash',
          color: '#191919',
          header: 'Укажите кассы в магазине'
        },
        {
          order: 5,
          stage: 'products',
          header: 'Добавьте продукты на полки'
        }
      ],
    }),
    methods: {
      bindClickOnElement(element) {
        if (element.getAttribute('category')) {
          switch (this.currentStage) {
            case 1:
              this.showCategoryPopup = true;
              break;
            case 3:
              if (element.tagName !== 'image') {
                replaceElement(element);
              } else {
                break;
              }
            case 5:
              this.showProductsPopup = true;
              break;
            default:
              element.setAttribute('style', `fill: ${this.stages[this.currentStage].color}; stroke: #000`);
              element.setAttribute('category', this.stages[this.currentStage].stage);
              break;
          }
        }
      },
      replaceElementToImage(element) {
        const image = document.createElementNS('http://www.w3.org/2000/svg', 'image');
        image.setAttributeNS('http://www.w3.org/1999/xlink', 'xlink:href', './door.png');
      },
      replaceElementToRect(element) {

      },
      sendMap() {
        let blocks = document.getElementsByClassName('clickable');
        let map = [];
        Array.from(blocks)
          .forEach((val, i) => {
            let idArray = val.id.split(':');
            if (val.getAttribute('category') != undefined) {
              map[i] = {
                x: idArray[1],
                y: idArray[2],
                blockType: val.category,
                category: {}
              };
            }
          });
        const data = {
          latitude: Math.random(),
          longitude: Math.random(),
          width: document.getElementById('svg').viewBox.baseVal.width / 100,
          height: document.getElementById('svg').viewBox.baseVal.height / 100,
          floor: 1,
          blocks: map.filter(it => it.x != null)
        };
        axios.post('http://192.168.42.55:8080/map/add', data)
          .then(function (response) {
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          });
        console.log(json);
      },
      goNextStep() {
        if (this.currentStage + 1 > this.stages.length - 1) {
          this.currentStage++;
        } else {
          this.sendMap();
          this.$router.push('/');
        }
      },
      goPrevStep() {
        if (this.currentStage !== 0) {
          this.currentStage--;
        }
      },
      init() {

      },
    },
    mounted() {
      let angleScale = {
        angle: 0,
        scale: 1,
      };
      const scalable = createGrid(this.mapWidth, this.mapHeight);
      scalable.id = 'svg';
      const container = document.getElementById('svgmap');
      container.append(scalable);
      const obj = document.getElementsByClassName('clickable');
      
    }
  };

</script>
