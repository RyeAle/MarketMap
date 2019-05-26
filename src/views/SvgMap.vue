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
  import {createGrid} from '../api/svg';
  import interact from 'interactjs';

  export default {
    data: () => ({
      currentStep: 0,
      stepsEnum: [
        {
          step: 'shelf',
          color: '#F9EAC0',
          category: {
            // step: 'shelfCategory',
            id: '',
            products: {}
          }
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
      goNextStep() {
        this.currentStep++;
        if (this.currentStep > 4) {
          this.sendMap();
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
        const json = JSON.stringify({
          latitude: Math.random(),
          longitude: Math.random(),
          width: document.getElementById("svg").width,
          height:  document.getElementById("svg").height,
          floor: 1,
          blocks: map
        });
        console.log(json);
      }
    },
    mounted() {
      let angleScale = {
        angle: 0,
        scale: 1,
      };
      let resetTimeout;
      const scalable = createGrid(10, 10);
      scalable.id = "svg";
      const container = document.getElementsByClassName('content')[0];
      container.append(scalable);
      const obj = document.getElementsByClassName('clickable');
      for (let i = 0; i < obj.length; i++) {
        let elem = obj[i];
        obj[i].addEventListener('click', () => {
          if (obj[i].getAttribute('category') !== undefined) {
            obj[i].setAttribute('style', `fill: ${this.stepsEnum[this.currentStep].color}; stroke: #000`);
            obj[i].setAttribute('category', this.stepsEnum[this.currentStep].step);
            if (this.currentStep == 2 && elem.tagName != 'image') {
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
        });
      }
      interact(container)
        .gesturable({
          onstart: function (event) {
            angleScale.angle -= event.angle;

            clearTimeout(resetTimeout);
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
          // keep the dragged position in the data-x/data-y attributes
          x = (parseFloat(target.getAttribute('data-x')) || 0) + event.dx,
          y = (parseFloat(target.getAttribute('data-y')) || 0) + event.dy;

        // translate the element
        target.style.webkitTransform =
          target.style.transform =
            'translate(' + x + 'px, ' + y + 'px)';

        // update the position attributes
        target.setAttribute('data-x', x);
        target.setAttribute('data-y', y);
      }

      function reset() {
        scalable.style.webkitTransform = scalable.style.transform = 'scale(1)';

        angleScale.angle = 0;
        angleScale.scale = 1;
      }

      window.dragMoveListener = dragMoveListener;
    }
  };
</script>

<style>
  .content {
    width: 90%;
    height: 90%;
    touch-action: none;
    z-index: 100;
    margin: 10px auto auto;
  }
</style>
