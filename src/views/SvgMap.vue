
<template>
  <div>
    <div class="content"></div>
    <!--<v-dialog v-model="dialog" width="90%"/>-->
    <button
      @click="goNextStep"
      style="position: absolute; bottom: 0; background: #666; width: 100px; height: 50px;">
      Go next
    </button>
    <button
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
    data: () => ({
      currentStep: 0,
      stepsEnum: [
        {
          step: 'shelf',
          color: '#F9EAC0',
          header: 'Отметьте существующие стеллажи с товарами на сетке'
        },
        {
          step: 'shelfCategory',
          id: '',
          products: {},
          header: 'Обозначьте секции с продуктами'
        },
        {
          step: 'pass',
          color: '#EBF4FC'
        },
        {
          step: 'entry',
          color: '#515151'
        },
        {
          step: 'cash',
          color: '#515151'
        },
      ]
    }),
    methods: {
      eventFunction(elem) {
        if (elem.getAttribute('category') !== undefined) {
          if (this.stepsEnum[this.currentStep].color != undefined) {
            elem.setAttribute('style', `fill: ${this.stepsEnum[this.currentStep].color}; stroke: #000`);
          }
          elem.setAttribute('category', this.stepsEnum[this.currentStep].step);
          if (this.currentStep == 3 && elem.tagName != 'image') {
            const image = document.createElementNS('http://www.w3.org/2000/svg', 'image');
            image.setAttributeNS('http://www.w3.org/1999/xlink', 'xlink:href', './door.png');
            image.setAttribute('width', elem.width.baseVal.value);
            image.setAttribute('height', elem.height.baseVal.value);
            image.setAttribute('x', elem.x.baseVal.value);
            image.setAttribute('y', elem.y.baseVal.value);
            image.setAttribute('id', elem.id);
            image.setAttribute('class', 'clickable');
            obj[i].parentNode.replaceChild(image, obj[i]);
          } else if (this.currentStep == 3) {
            const block = document.createElementNS('http://www.w3.org/2000/svg', 'rect');
            block.setAttribute('style', 'stroke:#000; fill:#FFF');
            block.setAttribute('width', elem.width.baseVal.value);
            block.setAttribute('height', elem.height.baseVal.value);
            block.setAttribute('x', elem.x.baseVal.value);
            block.setAttribute('y', elem.y.baseVal.value);
            block.setAttribute('class', 'clickable');
            block.setAttribute('id', elem.id);
          }
        } else {
          obj[i].setAttribute('category', this.stepsEnum[this.currentStep].step);
        }
      },
      goNextStep() {
        switch (this.currentStep) {
          case 0:
            this.currentStep++;
            document.querySelectorAll('.clickable')
              .forEach(val => {
                val.removeEventListener('click', eventFunction);
              });
            document.querySelectorAll('[category="shelf"]')
              .forEach(shelf => {
                shelf.addEventListener('click', (event) => {
                  let targetId = event.target.id;
                  console.log(targetId);
                });
              });
            break;
          case 1:
            break;
          case 5:
            this.sendMap();
          // this.currentStep;
        }
      },
      goPrevStep() {
        this.currentStep--;
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
      }
    },
    mounted() {
      let angleScale = {
        angle: 0,
        scale: 1,
      };
      const scalable = createGrid(10, 10);
      scalable.id = 'svg';
      const container = document.getElementsByClassName('content')[0];
      container.append(scalable);
      const obj = document.getElementsByClassName('clickable');
      for (let i = 0; i < obj.length; i++) {
        let elem = obj[i];
        elem.addEventListener('click', eventFunction(elem));
      }
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
  };
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
