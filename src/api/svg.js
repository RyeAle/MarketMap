const blockSize = 100;

export default function objectToSvg(floor) {
  console.log(floor);
  const svg = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
  svg.setAttribute('width', '100%');
  svg.setAttribute('height', '100%');
  svg.setAttribute('viewBox', `0 0 ${blockSize * floor.width} ${blockSize * floor.height}`);
  floor.blocks.forEach(block => {
    const b = document.createElementNS("http://www.w3.org/2000/svg", `${block.category ? "image" : "rect"}`);
    if (b.tagName == "image")
      b.setAttributeNS('http://www.w3.org/1999/xlink', 'xlink:href', `${block.category ? "http://192.168.43.95:8080" + block.category.logoUrl : "http://placekitten.com/32/32"}`);
    b.setAttribute('width', blockSize.toString());
    b.setAttribute('height', blockSize.toString());
    b.setAttribute('x', block.x * blockSize);
    b.setAttribute('y', (floor.height - block.y) * blockSize);
    b.setAttribute('id', `${b.tagName == "image" ? "shelf" : "pass"}:${block.id}`);
    b.setAttribute('class', `${b.tagName == "image" ? "shelf" : "pass"}`);
    svg.append(b);
  });
  return svg;
}

// n - x, m - y
export function createGrid(n, m) {
  const svg = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
  svg.setAttribute('width', '100%');
  svg.setAttribute('height', '100%');
  svg.setAttribute('viewBox', `0 0 ${blockSize * n} ${blockSize * m}`);
  for (let i = 0; i < n; i++) {
    for (let j = 0; j < m; j++) {
      const block = document.createElementNS('http://www.w3.org/2000/svg', 'rect');
      block.setAttribute('style', 'stroke:#000; fill:#FFF');
      block.setAttribute('width', blockSize.toString());
      block.setAttribute('height', blockSize.toString());
      block.setAttribute('x', j * blockSize);
      block.setAttribute('y', i * blockSize);
      block.setAttribute('class', 'clickable');
      block.setAttribute('id', `unmapped:${j}:${i}`);
      svg.append(block);
    }
  }
  return svg;
}

export function resizeSvg(svg, n, m) {
  let deltaN = Math.abs(n - svg.viewBox.baseVal.width / blockSize);
  const nOdd = deltaN % 2;
  deltaN -= nOdd;
  let deltaM = Math.abs(m - svg.viewBox.baseVal.height / blockSize);
  const mOdd = deltaM % 2;
  deltaM -= mOdd;
  svg.childNodes.forEach(it => {
    it.setAttribute('x', it.x.baseVal.value + deltaN * blockSize / 2);
    it.setAttribute('y', it.y.baseVal.value + deltaM * blockSize / 2);
    const i = it.id.indexOf(':');
    it.setAttribute('id', `${it.id.substring(0, i)}:${it.x.baseVal.value / blockSize + deltaN / 2}:${it.y.baseVal.value / blockSize + deltaM / 2}`);
    // TODO
  });
  for (let i = 0; i <= n + deltaN; i++) { // draw left column
    for (let j = 0; j < deltaN / 2; j++) {
      const block = document.createElementNS('http://www.w3.org/2000/svg', 'rect');
      block.setAttribute('style', 'stroke:#000; fill:#FFF');
      block.setAttribute('width', blockSize.toString());
      block.setAttribute('height', blockSize.toString());
      block.setAttribute('x', j * blockSize);
      block.setAttribute('y', i * blockSize);
      block.setAttribute('class', 'clickable');
      block.setAttribute('id', `unmapped:${j}:${i}`);
      svg.prepend(block);
    }
  }

  for (let i = 0; i <= deltaN; i++) { // draw highest row
    for (let j = deltaN / 2; j <= n - deltaN; j++) {
      const block = document.createElementNS('http://www.w3.org/2000/svg', 'rect');
      block.setAttribute('style', 'stroke:#000; fill:#FFF');
      block.setAttribute('width', blockSize.toString());
      block.setAttribute('height', blockSize.toString());
      block.setAttribute('x', j * blockSize);
      block.setAttribute('y', i * blockSize);
      block.setAttribute('class', 'clickable');
      block.setAttribute('id', `unmapped:${j}:${i}`);
      svg.prepend(block);
    }
  }

  for (let i = 0; i <= n + deltaN; i++) { // draw right column
    for (let j = svg.viewBox.baseVal.width / blockSize + deltaN / 2; j < n; j++) {
      const block = document.createElementNS('http://www.w3.org/2000/svg', 'rect');
      block.setAttribute('style', 'stroke:#000; fill:#FFF');
      block.setAttribute('width', blockSize.toString());
      block.setAttribute('height', blockSize.toString());
      block.setAttribute('x', j * blockSize);
      block.setAttribute('y', i * blockSize);
      block.setAttribute('class', 'clickable');
      block.setAttribute('id', `unmapped:${j}:${i}`);
      svg.append(block);
    }
  }

  for (let i = svg.viewBox.baseVal.height / blockSize + deltaM / 2; i <= n + deltaM; i++) { // draw lowest row
    for (let j = deltaN / 2; j <= n - deltaN; j++) {
      const block = document.createElementNS('http://www.w3.org/2000/svg', 'rect');
      block.setAttribute('style', 'stroke:#000; fill:#FFF');
      block.setAttribute('width', blockSize.toString());
      block.setAttribute('height', blockSize.toString());
      block.setAttribute('x', j * blockSize);
      block.setAttribute('y', i * blockSize);
      block.setAttribute('class', 'clickable');
      block.setAttribute('id', `unmapped:${j}:${i}`);
      svg.prepend(block);
    }
  }
  svg.setAttribute('viewBox', `0 0 ${blockSize * n} ${blockSize * m}`);
}

function setAttrs(elem) {
  for (var i = 1; i < arguments.length; i+=2) {
    elem.setAttribute(arguments[i], arguments[i+1]);
  }
}
