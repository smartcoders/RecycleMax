require 'rubygems'
require 'rspec'

class Tex
  attr_accessor :text
  
  def initialize(text)
    @text = text
  end
  
  def compile
    matches = @text.match(/(.*)"(.+)"(.*)/)
      if matches
      @text = "#{matches[1]}``#{matches[2]}''#{matches[3]}"
    end
  end
end

describe Tex do
  it 'should have a text' do
    text = "my dam'n text"
    tex = Tex.new text
    tex.text.should == text
  end
    
  it 'should translate the text to tex format' do
    original_text = 'my phrase with "my quote"'
    compiled_text = "my phrase with ``my quote''"
    tex = Tex.new original_text
    tex.compile.should == compiled_text 
  end

  it 'should translate the text to tex format with double quote' do
    original_text = 'my phrase with "my quote" and second "my quote"'
    compiled_text = "my phrase with ``my quote'' and second ``my quote''"
    tex = Tex.new original_text
    tex.compile.should == compiled_text 
  end
end

