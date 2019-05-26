<template>
  <div>
    <h4>{{stages[currentStage].header}}</h4>
    <div id="svgmap" class="content"></div>
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
    <v-dialog
      v-model="showCategoryPopup"
      width="500"
      scrollable
    >
      <category-item v-for="item in categoryList" :key="item.id" :name="item.name" :imgUrl="item.logoUrl">

      </category-item>
    </v-dialog>
  </div>
</template>

<script>
  import { createGrid, dropMarkup } from '../api/svg';
  import interact from 'interactjs';
  import axios from 'axios';
  import CategoryItem from '../components/CategoryItem.vue';

  export default {
    components: { CategoryItem },
    props: {
      mapWidth: {
        type: Number,
        default: 10
      },
      mapHeight: {
        type: Number,
        default: 10
      },
    },
    data: () => ({
      currentCategoryPopupShelf: {},
      currentStage: 0,
      currentBindingBlock: {},
      showCategoryPopup: false,
      showProductsPopup: false,
      categoryList: [],
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
      bindClickOnElement() {
        let element = this.currentBindingBlock;
        if (element.getAttribute('category')) {
          switch (this.currentStage) {
            case 1:
              this.showCategoryPopup = true;
              break;
            case 3:
              if (element.tagName !== 'image') {
                replaceElement(element);
              }
              break;
            case 5:
              this.showProductsPopup = true;
              break;
            default:
              console.log('hello!!!');
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
      },
      goNextStep() {
        console.log(this.currentStage, this.stages.length - 1, this.currentStage > this.stages.length - 1);
        if (this.currentStage < this.stages.length - 1) {
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
    created() {
      axios.get('http://192.168.42.55:8080/category')
        .then(res => {
          this.categoryList = res.data;
        })
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
      const mapBlocks = document.querySelectorAll('.clickable');
      mapBlocks.forEach(block => {
          block.addEventListener('click', (event) => {
              let element = block;
              console.log(event);
              if (element.getAttribute('category')) {
                switch (this.currentStage) {
                  case 1:
                    if (event.target.style.fill == 'rgb(249, 234, 192)') {
                      this.showCategoryPopup = true;
                      this.currentCategoryPopupShelf = event.target;
                    }
                    break;
                  case 3:
                    if (element.tagName !== 'image') {
                      replaceElement(element);
                    }
                    break;
                  case 5:
                    this.showProductsPopup = true;
                    break;
                  default:
                    console.log('я от бабушки ушел и от дедушки ушел');
                    break;
                }
              } else {


                switch (this.currentStage) {
                  case 1:
                    this.showCategoryPopup;
                    break;
                  case 3:
                    if (element.tagName !== 'image') {
                      replaceElement(element);
                    }
                    break;
                  case 5:
                    this.showProductsPopup = true;
                    break;
                  default:
                    console.log('hello!!!');
                    element.setAttribute('style', `fill: ${this.stages[this.currentStage].color}; stroke: #000`);
                    element.setAttribute('category', this.stages[this.currentStage].stage);
                    break;
                }
              }
            }
          );
        }
      );

      // drag and scale
      interact(container)
        .gesturable({
          onstart: function (event) {
            angleScale.angle -= event.angle;
            scalable.classList.remove('reset');
          },
          onmove: function (event) {
            const currentAngle = event.angle + angleScale.angle;
            const currentScale = event.scale * angleScale.scale;

            scalable.style.webkitTransform =
              scalable.style.transform =
                'rotate(' + currentAngle + 'deg)' + 'scale(' + currentScale + ')';

            dragMoveListener(event);
          }
        })
        .draggable({
          onmove: dragMoveListener
        });

      function dragMoveListener(event) {
        var target = event.target,
          x = (parseFloat(target.getAttribute('data-x')) || 0) + event.dx,
          y = (parseFloat(target.getAttribute('data-y')) || 0) + event.dy;

        target.style.webkitTransform =
          target.style.transform =
            'translate(' + x + 'px, ' + y + 'px)';

        target.setAttribute('data-x', x);
        target.setAttribute('data-y', y);
      }

      window.dragMoveListener = dragMoveListener;
    }
  }
  ;

</script>

<style>
  .content {
    width: 90%;
    height: 90%;
    z-index: 100;
    margin: 10px auto auto;
  }

  #svg {
    display: block;
    touch-action: none;
  }
</style>
