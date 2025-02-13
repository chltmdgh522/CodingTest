def is_inside_planet(x, y, cx, cy, r)
  (x - cx) ** 2 + (y - cy) ** 2 < r ** 2
end

def count_transitions(x1, y1, x2, y2, planets)
  count = 0
  
  planets.each do |cx, cy, r|
    inside_start = is_inside_planet(x1, y1, cx, cy, r)
    inside_end = is_inside_planet(x2, y2, cx, cy, r)
    
    count += 1 if inside_start != inside_end
  end
  
  count
end

def main
  t = gets.to_i
  results = []
  
  t.times do
    x1, y1, x2, y2 = gets.split.map(&:to_i)
    n = gets.to_i
    planets = []
    
    n.times do
      cx, cy, r = gets.split.map(&:to_i)
      planets << [cx, cy, r]
    end
    
    results << count_transitions(x1, y1, x2, y2, planets)
  end
  
  puts results
end

main
