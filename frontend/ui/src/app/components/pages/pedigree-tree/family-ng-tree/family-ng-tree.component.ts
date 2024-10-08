import {Component, ElementRef, Input, SimpleChanges} from '@angular/core';
import * as d3 from 'd3';
import {FamilyMember} from "../../../../interfaces/models/society/family-member";

@Component({
  selector: 'app-family-ng-tree',
  templateUrl: './family-ng-tree.component.html',
  styleUrl: './family-ng-tree.component.css'
})
export class FamilyNgTreeComponent {
  @Input() data!: FamilyMember;
  @Input() width = 900;
  @Input() height = 500;

  private svg: any;
  private tree: any;
  private root: any;

  constructor(private elementRef: ElementRef) {}

  ngOnInit() {
    this.createSvg();
    if (this.data) {
      this.updateChart();
    }
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['data'] && !changes['data'].firstChange) {
      this.updateChart();
    }
  }

  private createSvg() {
    const element = this.elementRef.nativeElement.querySelector('div');
    const margin = { top: 100, right: 50, bottom: 100, left: 50 };
    const width = this.width - margin.left - margin.right;
    const height = this.height - margin.top - margin.bottom;

    this.svg = d3.select(element).append('svg')
      .attr('width', width + margin.left + margin.right)
      .attr('height', height + margin.top + margin.bottom)
      .append('g')
      .attr('transform', `translate(${margin.left},${margin.top})`);

    this.tree = d3.tree().size([width, height]);
  }

  private updateChart() {
    // Clear previous chart
    this.svg.selectAll('*').remove();

    // Create the tree layout
    this.root = d3.hierarchy(this.data, d => d.parents);
    this.tree(this.root);

    // Add links
    const link = this.svg.selectAll('.link')
      .data(this.root.links())
      .enter().append('path')
      .attr('class', 'link')
      .attr('fill', 'none')
      .attr('stroke', '#000')
      .attr('shape-rendering', 'crispEdges')
      .attr('d', this.connect.bind(this));

    // Add nodes
    const node = this.svg.selectAll('.node')
      .data(this.root.descendants())
      .enter().append('g')
      .attr('class', 'node')
      .attr('transform', d => `translate(${d.x},${this.height - d.y})`);

    // Add rectangles to nodes
    node.append('rect')
      .attr('width', 140)
      .attr('height', 80)
      .attr('fill', 'tan')
      .attr('x', -70)
      .attr('y', -40);

    // Add name text
    node.append('text')
      .attr('font-size', '16px')
      .attr('fill', 'black')
      .attr('text-anchor', 'middle')
      .attr('y', -15)
      .text(d => d.data.name);

    // Add birth/death years
    node.append('text')
      .attr('font-size', '12px')
      .attr('text-anchor', 'middle')
      .attr('y', 10)
      .text(d => `${d.data.born}–${d.data.died || ''}`);

    // Add location
    node.append('text')
      .attr('font-size', '11px')
      .attr('text-anchor', 'middle')
      .attr('y', 28)
      .text(d => d.data.location);
  }

  private connect(d: any) {
    return `M${d.source.x},${this.height - d.source.y}
            V${this.height - (3 * d.source.y + 4 * d.target.y) / 7}
            H${d.target.x}
            V${this.height - d.target.y}`;
  }
}
