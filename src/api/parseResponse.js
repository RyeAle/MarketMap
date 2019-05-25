var blockTypeEnum = Object.freeze([
    'entry', 'quit', 'pass', 'shelf'
  ]
);

var jsonProp = [
  {
    latitude: '222111',
    longitude: '333111',
    width: 6,
    height: 7,
    floor: 1,
    blocks:
      [
        {
          x: '2',
          y: '3',
          id: 1,
          blockType: 'entry',
          category: {
            id: 228,
            name: "kek",
            logoUrl: "/img/kek/lol"
          }
        }
      ]
  }
];

parseResponse(jsonProp);

function parseResponse(json) {
  json.forEach(value => {
    let { latitude, longitude, width, height, floor, blocks } = value;
    console.log(latitude,longitude,width,height,floor);
    blocks.forEach(value => {
      let { x, y, id, type } = value;
      console.log(x,y,id,type)
    });
  });
}

export default parseResponse;
